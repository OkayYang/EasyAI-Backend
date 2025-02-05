package com.easyai.client.custom.service.chat;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.mapper.EasyAiMessageMapper;
import com.easyai.client.base.service.IChatService;
import com.easyai.client.custom.controller.chat.vo.*;
import com.easyai.client.custom.enums.ChatStatusEnum;
import com.easyai.client.custom.enums.MessageStatusEnum;
import com.easyai.client.custom.enums.MessageStreamResponsePhaseEnum;
import com.easyai.client.custom.mapper.ChatCustomMapper;
import com.easyai.client.custom.mapper.ChatModelCustomMapper;
import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.client.custom.service.apikey.ApiKeyCustomService;
import com.easyai.client.langchain4j.platform.openai.domain.OpenAiErrorMessage;
import com.easyai.client.langchain4j.platform.openai.service.OpenAiService;
import com.easyai.client.langchain4j.service.EasyAiService;
import com.easyai.client.langchain4j.store.PersistentChatMemoryStore;
import com.easyai.client.langchain4j.utils.ParseInnerLLMError;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.core.utils.DateUtils;
import com.easyai.common.core.utils.uuid.UUID;
import com.easyai.common.security.utils.SecurityUtils;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.TokenUsage;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

import static com.easyai.client.custom.constant.EasyAIConstants.EASYAI_AI;
import static com.easyai.client.custom.constant.EasyAIConstants.EASYAI_USER;


/**
 * @author XuShiPing
 * @since 2024/12/17  18:58
 */
@Service
public class ChatCustomService implements IChatCustomService {

    @Autowired
    private ChatCustomMapper chatCustomMapper;

    @Autowired
    private ApiKeyCustomService apiKeyCustomService;

    @Autowired
    private ChatModelCustomMapper chatModelCustomMapper;

    @Autowired
    private EasyAiMessageCustomMapper easyAiMessageCustomMapper;

    @Autowired
    private EasyAiMessageMapper easyAiMessageMapper;

    @Autowired
    private PersistentChatMemoryStore persistentChatMemoryStore;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private IChatService chatService;


    @Override
    public void deleteChat(String session_id) {
        String email = SecurityUtils.getUsername();
        Chat chatBySessionId = chatCustomMapper.getChatBySessionId(email, session_id, ChatStatusEnum.EXIST.getValue());
        if (chatBySessionId == null) {
            throw new ServiceException("删除失败!会话不存在!");
        }
        chatCustomMapper.deleteChat(session_id, ChatStatusEnum.DELETE.getValue());
    }

    @Override
    public int updateChatTitle(EditTitleReqBody editTitleReqBody) {
        Chat chat = new Chat();
        String email = SecurityUtils.getUsername();
        Chat chatBySessionId = chatCustomMapper.getChatBySessionId(email, editTitleReqBody.getSessionId(), ChatStatusEnum.EXIST.getValue());
        if (chatBySessionId == null) {
            throw new ServiceException("更新失败!会话不存在!");
        }
        BeanUtils.copyProperties(editTitleReqBody, chat);
        return chatService.updateChat(chat);
    }


    @Override
    public List<ChatListRespBody> listAllChat() {
        String email = SecurityUtils.getUsername();
        List<Chat> chatList = chatCustomMapper.getChatListByEmailOrderByTime(email, ChatStatusEnum.EXIST.getValue());
        List<ChatListRespBody> chatListRespBodyList = new ArrayList<>();
        for (Chat chat : chatList) {
            ChatListRespBody respBody = new ChatListRespBody();
            BeanUtils.copyProperties(chat, respBody); // 复制单个对象
            chatListRespBodyList.add(respBody);
        }
        return chatListRespBodyList;
    }
    @Override
    @Transactional
    public Flux<ChatStreamResp<?>> chat(ChatStreamReqBody chatStreamReqBody) {
        // 1. 验证模型合法性
        ChatModel model = checkModelExist(chatStreamReqBody.getModelName());

        // 2. 验证或创建会话
        String email = SecurityUtils.getUsername();
        String sessionId;
        String parentId;

        if (chatStreamReqBody.getSession_id() != null) {
            sessionId = chatStreamReqBody.getSession_id();
            parentId = chatStreamReqBody.getParent_id();
            checkParentMessageExist(sessionId, parentId, email);
        } else {
            sessionId = UUID.randomUUID().toString();
            parentId = null;
            createChatSession(email,chatStreamReqBody.getUserMessage(),sessionId, chatStreamReqBody.getModelName());

        }

        // 3. 获取API密钥
        ApiKey apiKey = apiKeyCustomService.getRandomApiKey(model.getPlatform());

        // 4. 生成消息ID
        String userMessageId = UUID.randomUUID().toString();
        String aiMessageId = UUID.randomUUID().toString();


        // 6. 创建响应流
        Sinks.Many<ChatStreamResp<?>> sink = Sinks.many().unicast().onBackpressureBuffer();

        // 7. 配置AI服务(目前默认openai)
        StreamingChatLanguageModel chatModel = OpenAiStreamingChatModel.builder()
                .apiKey(apiKey.getApiKey())
                .modelName(chatStreamReqBody.getModelName())
                .build();

        // 8.配置记忆历史
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(persistentChatMemoryStore)
                .build();

        EasyAiService easyAiService = AiServices.builder(EasyAiService.class)
                .streamingChatLanguageModel(chatModel)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
        // 8. 处理AI流式响应
        StringBuffer stringBuffer = new StringBuffer();
        sink.tryEmitNext(new ChatStreamResp<>(
                sessionId,
                new ChatStreamStartResp(parentId, userMessageId,
                        aiMessageId, chatStreamReqBody.getModelName(),
                        DateUtils.getNowDate()),
                MessageStreamResponsePhaseEnum.START.getValue()));
        easyAiService.tokenStream(sessionId,chatStreamReqBody.getUserMessage())
                .onNext(val -> {
                    stringBuffer.append(val);
                    sink.tryEmitNext(new ChatStreamResp<>(
                            sessionId,
                            val,
                            MessageStreamResponsePhaseEnum.CHAT.getValue()
                    ));
                })
                .onError(err -> {
                    OpenAiErrorMessage openAiErrorMessage =
                            ParseInnerLLMError.parseOpenAiHttpExceptionMessage(err);

                    openAiService.handleInnerError(
                            sessionId, aiMessageId, email,
                            chatStreamReqBody.getModelName(), apiKey, openAiErrorMessage
                    );
                    if (parentId==null){
                        chatCustomMapper.deleteChatBySessionId(sessionId);
                    }
                    sink.tryEmitNext(new ChatStreamResp<>(
                            sessionId,
                            "内部错误,不消耗任何token！",
                            MessageStreamResponsePhaseEnum.ERROR.getValue()
                    ));
                    sink.tryEmitComplete();
                })
                .onComplete(response -> {
                    // 这都是一些不紧急的操作，下面操作可以异步，
                    // 只是更新数据库的一些token信息，其实扣除完token数即可返回用户了
                    String finishReason = response.finishReason().toString();
                    TokenUsage tokenUsage = response.tokenUsage();
                    int outputToken = tokenUsage.outputTokenCount();
                    int inputToken = tokenUsage.inputTokenCount();

                    EasyAiMessage userMessage = new EasyAiMessage();
                    userMessage.setMessageId(userMessageId);
                    userMessage.setSessionId(sessionId);
                    userMessage.setEmail(email);
                    userMessage.setContent(chatStreamReqBody.getUserMessage());
                    userMessage.setRole(EASYAI_USER);
                    userMessage.setParentId(parentId);
                    userMessage.setModelName(chatStreamReqBody.getModelName());
                    userMessage.setToken((long) inputToken);
                    easyAiMessageMapper.insertEasyAiMessage(userMessage);

                    EasyAiMessage aiMessage = new EasyAiMessage();
                    aiMessage.setMessageId(aiMessageId);
                    aiMessage.setSessionId(sessionId);
                    aiMessage.setEmail(email);
                    aiMessage.setContent(String.valueOf(stringBuffer));
                    aiMessage.setRole(EASYAI_AI);
                    aiMessage.setParentId(userMessageId);
                    aiMessage.setModelName(chatStreamReqBody.getModelName());
                    aiMessage.setToken((long) outputToken);
                    easyAiMessageMapper.insertEasyAiMessage(aiMessage);

                    sink.tryEmitNext(new ChatStreamResp<>(
                            sessionId,
                            new ChatStreamCompleteResp(
                                    aiMessageId, inputToken, outputToken, finishReason
                            ),
                            MessageStreamResponsePhaseEnum.CONCLUDE.getValue()
                    ));
                    sink.tryEmitComplete();

                })
                .start();

        // 9. 返回响应流
        return sink.asFlux();

    }


    // 创建新对话逻辑
    private void createChatSession(String email,String userMessage, String session_id, String model_name) {
        Chat chat = new Chat();
        chat.setEmail(email);
        if (userMessage.length() > 30) {
            userMessage = userMessage.substring(0, 30);
        }
        chat.setTitle(userMessage);
        chat.setModelName(model_name);
        chat.setSessionId(session_id);
        chatCustomMapper.insertChat(chat);
    }

    /**
     * 不存在该模型名则抛出异常
     **/
    private ChatModel checkModelExist(String modelName) {
        ChatModel selectedModel = chatModelCustomMapper.getModelByName(modelName);
        if (selectedModel == null) {
            throw new RuntimeException("未查找到该模型!");
        }
        return selectedModel;
    }

    /**
     * 根据请求提供的sessionId 查询数据库，检测是否存在sessionId 和回复的id，不存在则抛出异常
     *
     * @param session_id
     * @param parent_id
     * @param email
     */
    private void checkParentMessageExist(String session_id, String parent_id, String email) {
        EasyAiMessage easyAiMessage = easyAiMessageCustomMapper.selectEasyAiMessageByParentId(session_id, parent_id, email);
        if (easyAiMessage == null) {
            throw new RuntimeException("异常请求!");
        }
    }

}
