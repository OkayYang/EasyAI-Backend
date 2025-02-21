package com.easyai.client.custom.service.chat;

import com.easyai.client.base.domain.*;
import com.easyai.client.base.mapper.EasyAiMessageMapper;
import com.easyai.client.base.service.IChatService;
import com.easyai.client.custom.controller.chat.vo.*;
import com.easyai.client.custom.enums.ChatStatusEnum;
import com.easyai.client.custom.enums.MessageStreamResponsePhaseEnum;
import com.easyai.client.custom.mapper.ChatCustomMapper;
import com.easyai.client.custom.mapper.ChatModelCustomMapper;
import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.custom.service.apikey.ApiKeyCustomService;
import com.easyai.client.langchain4j.factory.ModelFactoryManager;
import com.easyai.client.langchain4j.platform.openai.domain.OpenAiErrorMessage;
import com.easyai.client.langchain4j.platform.openai.service.OpenAiService;
import com.easyai.client.langchain4j.service.EasyAiService;
import com.easyai.client.langchain4j.store.PersistentChatMemoryStore;
import com.easyai.client.langchain4j.utils.ParseInnerLLMError;
import com.easyai.client.springai.factory.SpringAiChatModelFactoryManager;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.core.utils.DateUtils;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.core.utils.uuid.UUID;
import com.easyai.common.security.utils.SecurityUtils;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.output.TokenUsage;
import dev.langchain4j.service.AiServices;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.easyai.client.custom.constant.EasyAIConstants.*;


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
    private UserCustomMapper userCustomMapper;

    @Autowired
    private PersistentChatMemoryStore persistentChatMemoryStore;

    @Autowired
    private ModelFactoryManager modelFactoryManager;

    @Autowired
    private SpringAiChatModelFactoryManager springAiChatModelFactoryManager;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private IChatService chatService;

    @Autowired
    private InMemoryChatMemory inMemoryChatMemory;


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

    @Transactional
    @Override
    public Flux<ChatStreamResp<?>> springAiChat(ChatStreamReqBody chatStreamReqBody) {
        String email = SecurityUtils.getUsername();
        // 1. 验证模型合法性以及用户token余额
        EasyAiChatModel model = checkModelExist(chatStreamReqBody.getModelName());
        User user = userCustomMapper.selectUserByUserName(email);
        Long balance = user.getPower();
        if (balance <= 0 && model.getPrice() > 0) {
            return Flux.just(new ChatStreamResp<>(
                    null,
                    "余额不足，无法进行对话，请充值！",
                    MessageStreamResponsePhaseEnum.ERROR.getValue()
            ));
        }
        // 2. 验证或创建会话
        // 2. 验证或创建会话
        String sessionId;
        String parentId;
        String title = null;

        if (chatStreamReqBody.getSession_id() != null) {
            sessionId = chatStreamReqBody.getSession_id();
            parentId = chatStreamReqBody.getParent_id();
            checkParentMessageExist(sessionId, parentId, email);
        } else {
            sessionId = UUID.randomUUID().toString();
            parentId = null;
            title = chatStreamReqBody.getUserMessage();
            if (title.length() > 30) {
                title = title.substring(0, 30);
            }
        }
        // 3. 获取API密钥
        ApiKey apiKey = apiKeyCustomService.getRandomApiKey(model.getPlatform());

        // 4. 生成消息ID
        String userMessageId = UUID.randomUUID().toString();
        String aiMessageId = UUID.randomUUID().toString();


        // 6. 创建响应流
        Sinks.Many<ChatStreamResp<?>> sink = Sinks.many().unicast().onBackpressureBuffer();

        // 7. 配置AI服务
        org.springframework.ai.chat.model.ChatModel chatModel = springAiChatModelFactoryManager.createChatModel(apiKey, model);

        // 8.处理AI流式响应
        sink.tryEmitNext(new ChatStreamResp<>(sessionId,
                new ChatStreamStartResp(parentId, userMessageId,
                        aiMessageId, chatStreamReqBody.getModelName(), title,
                        DateUtils.getNowDate().getTime()),
                MessageStreamResponsePhaseEnum.START.getValue()));

        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(inMemoryChatMemory, sessionId, MEMORY_SIZE)
                )
                .build();

        String finalTitle = title;
        boolean isDeepseek = model.getModelName().toLowerCase().contains("deepseek");
        AtomicBoolean isThinking = new AtomicBoolean(true);
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder thinkBuffer = new StringBuilder();
        String thinkStartTag = "<think>";
        String thinkEndTag = "</think>";
        chatClient.prompt(chatStreamReqBody.getUserMessage())
                .stream()
                .chatResponse()
                .doOnNext(chatResponse -> {
                    String text = chatResponse.getResult().getOutput().getText();
                    if (isDeepseek && isThinking.get()) {
                        boolean isStartTag = thinkStartTag.equals(text);
                        boolean isEndTag = thinkEndTag.equals(text);
                        if (!isStartTag && !isEndTag) {
                            if ("\n\n".equals(text) && !thinkBuffer.isEmpty()) {
                                sink.tryEmitNext(new ChatStreamResp<>(
                                        sessionId,
                                        text,
                                        MessageStreamResponsePhaseEnum.THINKING.getValue()
                                ));
                                thinkBuffer.append(text);
                            }else if(!"\n\n".equals(text)){
                                sink.tryEmitNext(new ChatStreamResp<>(
                                        sessionId,
                                        text,
                                        MessageStreamResponsePhaseEnum.THINKING.getValue()
                                ));
                                thinkBuffer.append(text);
                            }

                        } else if (isEndTag) {
                            isThinking.set(false);
                        }
                    } else {
                        sink.tryEmitNext(new ChatStreamResp<>(
                                sessionId,
                                text,
                                MessageStreamResponsePhaseEnum.CHAT.getValue()
                        ));
                        textBuffer.append(text);
                    }
                    String finishReason = chatResponse.getResult().getMetadata().getFinishReason();
                    if (CHAT_STATUS_STOP.equalsIgnoreCase(finishReason)) {
                        Integer inputToken = chatResponse.getMetadata().getUsage().getPromptTokens() * model.getPrice();
                        Integer outputToken = chatResponse.getMetadata().getUsage().getCompletionTokens() * model.getPrice();
                        if (parentId == null) {
                            createChatSession(email, finalTitle, sessionId, chatStreamReqBody.getModelName());
                        }

                        EasyAiMessage userMessage = new EasyAiMessage();
                        userMessage.setMessageId(userMessageId);
                        userMessage.setSessionId(sessionId);
                        userMessage.setEmail(email);
                        userMessage.setContent(chatStreamReqBody.getUserMessage());
                        userMessage.setRole(EASYAI_USER);
                        userMessage.setParentId(parentId);
                        userMessage.setModelName(chatStreamReqBody.getModelName());
                        userMessage.setToken(inputToken);
                        userMessage.setCreateAt(DateUtils.getNowDate().getTime());
                        easyAiMessageMapper.insertEasyAiMessage(userMessage);

                        EasyAiMessage aiMessage = new EasyAiMessage();
                        aiMessage.setMessageId(aiMessageId);
                        aiMessage.setSessionId(sessionId);
                        aiMessage.setEmail(email);
                        aiMessage.setContent(textBuffer.toString());
                        String thinkText = thinkBuffer.toString();
                        aiMessage.setThinkingContent(StringUtils.hasText(thinkText) ? thinkText : null);
                        aiMessage.setRole(EASYAI_AI);
                        aiMessage.setParentId(userMessageId);
                        aiMessage.setModelName(chatStreamReqBody.getModelName());
                        aiMessage.setToken(outputToken);
                        aiMessage.setCreateAt(DateUtils.getNowDate().getTime());
                        easyAiMessageMapper.insertEasyAiMessage(aiMessage);
                        user.setPower(Math.max(0, balance - outputToken - inputToken));
                        userCustomMapper.updateUser(user);
                        sink.tryEmitNext(new ChatStreamResp<>(
                                sessionId,
                                new ChatStreamCompleteResp(
                                        aiMessageId, inputToken, outputToken, finishReason
                                ),
                                MessageStreamResponsePhaseEnum.CONCLUDE.getValue()
                        ));
                        sink.tryEmitComplete();

                    }

                })
                .doOnError(throwable -> {
                    sink.tryEmitNext(new ChatStreamResp<>(
                            sessionId,
                            "内部错误,不消耗任何token！",
                            MessageStreamResponsePhaseEnum.ERROR.getValue()
                    ));
                    sink.tryEmitComplete();

                })
                .doOnComplete(sink::tryEmitComplete).subscribe();
        return sink.asFlux();

    }


    @Override
    @Transactional
    public Flux<ChatStreamResp<?>> chat(ChatStreamReqBody chatStreamReqBody) {
        String email = SecurityUtils.getUsername();
        // 1. 验证模型合法性以及用户token余额
        EasyAiChatModel model = checkModelExist(chatStreamReqBody.getModelName());
        User user = userCustomMapper.selectUserByUserName(email);
        Long balance = user.getPower();
        if (balance <= 0) {
            return Flux.just(new ChatStreamResp<>(
                    null,
                    "余额不足，无法进行对话，请充值！",
                    MessageStreamResponsePhaseEnum.ERROR.getValue()
            ));
        }


        // 2. 验证或创建会话
        String sessionId;
        String parentId;
        String title = null;

        if (chatStreamReqBody.getSession_id() != null) {
            sessionId = chatStreamReqBody.getSession_id();
            parentId = chatStreamReqBody.getParent_id();
            checkParentMessageExist(sessionId, parentId, email);
        } else {
            sessionId = UUID.randomUUID().toString();
            parentId = null;
            title = chatStreamReqBody.getUserMessage();
            if (title.length() > 30) {
                title = title.substring(0, 30);
            }
            createChatSession(email, title, sessionId, chatStreamReqBody.getModelName());

        }

        // 3. 获取API密钥
        ApiKey apiKey = apiKeyCustomService.getRandomApiKey(model.getPlatform());

        // 4. 生成消息ID
        String userMessageId = UUID.randomUUID().toString();
        String aiMessageId = UUID.randomUUID().toString();


        // 6. 创建响应流
        Sinks.Many<ChatStreamResp<?>> sink = Sinks.many().unicast().onBackpressureBuffer();

        // 7. 配置AI服务
        StreamingChatLanguageModel chatModel = modelFactoryManager.createModel(apiKey, model);

        // 8.配置记忆历史
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(MEMORY_SIZE)
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
                        aiMessageId, chatStreamReqBody.getModelName(), title,
                        DateUtils.getNowDate().getTime()),
                MessageStreamResponsePhaseEnum.START.getValue()));
        easyAiService.tokenStream(sessionId, chatStreamReqBody.getUserMessage())
                .onPartialResponse(val -> {
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
                    if (parentId == null) {
                        chatCustomMapper.deleteChatBySessionId(sessionId);
                    }
                    sink.tryEmitNext(new ChatStreamResp<>(
                            sessionId,
                            "内部错误,不消耗任何token！",
                            MessageStreamResponsePhaseEnum.ERROR.getValue()
                    ));
                    sink.tryEmitComplete();
                })
                .onCompleteResponse(response -> {
                    // 这都是一些不紧急的操作，下面操作可以异步，
                    // 只是更新数据库的一些token信息，其实扣除完token数即可返回用户了
                    String finishReason = response.finishReason().toString();
                    TokenUsage tokenUsage = response.tokenUsage();
                    Integer outputToken = tokenUsage.outputTokenCount() * model.getPrice();
                    Integer inputToken = tokenUsage.inputTokenCount() * model.getPrice();

                    EasyAiMessage userMessage = new EasyAiMessage();
                    userMessage.setMessageId(userMessageId);
                    userMessage.setSessionId(sessionId);
                    userMessage.setEmail(email);
                    userMessage.setContent(chatStreamReqBody.getUserMessage());
                    userMessage.setRole(EASYAI_USER);
                    userMessage.setParentId(parentId);
                    userMessage.setModelName(chatStreamReqBody.getModelName());
                    userMessage.setToken(inputToken);
                    userMessage.setCreateAt(DateUtils.getNowDate().getTime());
                    easyAiMessageMapper.insertEasyAiMessage(userMessage);

                    EasyAiMessage aiMessage = new EasyAiMessage();
                    aiMessage.setMessageId(aiMessageId);
                    aiMessage.setSessionId(sessionId);
                    aiMessage.setEmail(email);
                    aiMessage.setContent(String.valueOf(stringBuffer));
                    aiMessage.setRole(EASYAI_AI);
                    aiMessage.setParentId(userMessageId);
                    aiMessage.setModelName(chatStreamReqBody.getModelName());
                    aiMessage.setToken(outputToken);
                    aiMessage.setCreateAt(DateUtils.getNowDate().getTime());
                    easyAiMessageMapper.insertEasyAiMessage(aiMessage);
                    user.setPower(Math.max(0, balance - outputToken - inputToken));
                    userCustomMapper.updateUser(user);
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
    private void createChatSession(String email, String title, String session_id, String model_name) {
        Chat chat = new Chat();
        chat.setEmail(email);
        chat.setTitle(title);
        chat.setModelName(model_name);
        chat.setSessionId(session_id);
        chatCustomMapper.insertChat(chat);
    }

    /**
     * 不存在该模型名则抛出异常
     **/
    private EasyAiChatModel checkModelExist(String modelName) {
        EasyAiChatModel selectedModel = chatModelCustomMapper.getModelByName(modelName);
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
