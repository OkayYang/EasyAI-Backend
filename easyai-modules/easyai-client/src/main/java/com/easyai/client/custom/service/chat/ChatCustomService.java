package com.easyai.client.custom.service.chat;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.service.IChatService;
import com.easyai.client.base.service.impl.ChatServiceImpl;
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
import java.util.Date;
import java.util.List;

import static com.easyai.client.custom.constant.EasyAIConstants.EASYAI_AI;
import static com.easyai.client.custom.constant.EasyAIConstants.EASYAI_USER;


/**
 * @author XuShiPing
 * @since 2024/12/17  18:58
 */
@Service
public class ChatCustomService  implements IChatCustomService {

    @Autowired
    private ChatCustomMapper chatCustomMapper;

    @Autowired
    private ApiKeyCustomService apiKeyCustomService;

    @Autowired
    private ChatModelCustomMapper chatModelCustomMapper;

    @Autowired
    private EasyAiMessageCustomMapper easyAiMessageCustomMapper;

    @Autowired
    private PersistentChatMemoryStore store;

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
        BeanUtils.copyProperties(editTitleReqBody,chat);
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
        String givenModelName = chatStreamReqBody.getModelName();
        // 检测请求的模型是否存在
        ChatModel chatModel = checkModelExist(givenModelName);

        String email = SecurityUtils.getUsername();
        String session_id = chatStreamReqBody.getSession_id();
        String parent_id = chatStreamReqBody.getParent_id();

        // 这一步执行完毕后 session_id 只有两种可能，一种是 session_id 为null 或session_id是数据库中存且合法的
        if (session_id != null) {
            // 请求提供了 session_id  则判断是否存在
            checkExistChat(session_id, parent_id, email);
        }
        String userMessage = chatStreamReqBody.getUserMessage();
        String platform = chatModel.getPlatform();
        // 若未空 则新建会话
        if (session_id == null) {
            session_id = UUID.randomUUID().toString();
            addChat(email, session_id, givenModelName);
            parent_id = null;
        }

        // 随机获取该平台下的一个api秘钥
        ApiKey randomApiKey = apiKeyCustomService.getRandomApiKey(platform);
        StringBuffer stringBuffer = new StringBuffer();

        //初始化 提供者
        Sinks.Many<ChatStreamResp<?>> sink = Sinks.many().unicast().onBackpressureBuffer();

        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(store)
                .build();

        StreamingChatLanguageModel streamingChatLanguageModel = OpenAiStreamingChatModel.builder()
                .apiKey(randomApiKey.getApiKey())
                .modelName(givenModelName)
                .build();

        EasyAiService easyAiService = AiServices.builder(EasyAiService.class)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemoryProvider(chatMemoryProvider)
                .build();

        // 用户消息id生成
        String user_message_id = UUID.randomUUID().toString();
        // AI 消息生成 防止后面生成不好共享
        String ai_message_id = UUID.randomUUID().toString();

        // 基本数据预先插入
        easyAiMessageCustomMapper.insertEasyAiUserMessageWhenChat(user_message_id, session_id, email, userMessage, EASYAI_USER, parent_id, givenModelName);
        easyAiMessageCustomMapper.insertEasyAiAIMessageWhenChat(ai_message_id, session_id, email, EASYAI_AI, user_message_id, givenModelName);


        // 由于lambda 缘故，必须使用finalSession_id
        String session_id_cp = session_id;

        // 生产者 生产情况
        //预先传父message id 防止用户取消找不到父message id  时间与数据库对不上，但是感觉没必要 gpt自己都不显示时间
        sink.tryEmitNext(new ChatStreamResp<>(session_id_cp, new ChatStreamStartResp(parent_id,user_message_id,ai_message_id,givenModelName, new Date()), MessageStreamResponsePhaseEnum.START.getValue()));
        easyAiService.tokenStream(session_id_cp, userMessage)
                .onNext((val) -> {
                    System.out.println("gpt:" + val);
                    sink.tryEmitNext(new ChatStreamResp<>(session_id_cp, val, MessageStreamResponsePhaseEnum.CHAT.getValue()));
                }).onError(err -> {
                    OpenAiErrorMessage openAiErrorMessage = ParseInnerLLMError.parseOpenAiHttpExceptionMessage(err);
                    openAiService.handleInnerError(session_id_cp, ai_message_id, email, givenModelName, randomApiKey, openAiErrorMessage);
                    easyAiMessageCustomMapper.updateEasyAiMessageStatus(ai_message_id, MessageStatusEnum.INNER_ERROR.getValue());
                    sink.tryEmitNext(new ChatStreamResp<>(session_id_cp, "内部调用错误,不消耗任何token！", MessageStreamResponsePhaseEnum.ERROR.getValue()));
                    sink.tryEmitComplete();
                }).onComplete((response) -> {
                    // 这都是一些不紧急的操作，感觉下面操作可以异步 只是更新数据库的一些token信息，其实扣除完token数即可返回用户了
                    String finishReason = response.finishReason().toString();
                    TokenUsage tokenUsage = response.tokenUsage();
                    int output_token = tokenUsage.outputTokenCount();
                    int input_token = tokenUsage.inputTokenCount();
                    easyAiMessageCustomMapper.updateEasyAiMessageWhenComplete(ai_message_id, finishReason, output_token);
                    easyAiMessageCustomMapper.updateEasyAiParentMessageInputToken(user_message_id, input_token);
                    sink.tryEmitNext(new ChatStreamResp<>(session_id_cp, new ChatStreamCompleteResp(ai_message_id, input_token, output_token, finishReason), MessageStreamResponsePhaseEnum.CONCLUDE.getValue()));
                    System.out.println("gpt complete!");
                    sink.tryEmitComplete();
                }).start();

        // 消费者 用户
        return sink.asFlux().doOnNext((chatStreamResp) -> {
            if (MessageStreamResponsePhaseEnum.CHAT.getValue().equals(chatStreamResp.getPhase())) {
                stringBuffer.append((String) chatStreamResp.getContent());
            }
        }).doOnCancel(() -> {
            easyAiMessageCustomMapper.updateEasyAiMessageVisualWhenComplete(ai_message_id, String.valueOf(stringBuffer), "cancel");
            System.out.println("sink:cancel");
        }).doOnComplete(() -> {
            easyAiMessageCustomMapper.updateEasyAiMessageVisualWhenComplete(ai_message_id, String.valueOf(stringBuffer), FinishReason.STOP.toString());
            System.out.println("sink:complete");
        }).doOnError(err -> {
            // todo
            System.out.println("!!!!!!!!!!!!!" + err);
        });
    }


    // 创建新对话逻辑
    private void addChat(String email, String session_id, String model_name) {
        Chat insertChat = new Chat();
        insertChat.setEmail(email);
        insertChat.setModelName(model_name);
        insertChat.setSessionId(session_id);
        chatCustomMapper.insertChat(insertChat);
    }


    private boolean isModelExist(String modelName) {
        ChatModel selectedModel = chatModelCustomMapper.getModelByName(modelName);
        return selectedModel != null;
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
    private void checkExistChat(String session_id, String parent_id, String email) {
        EasyAiMessage easyAiMessage = easyAiMessageCustomMapper.selectEasyAiMessageByParentId(session_id, parent_id, email);
        if (easyAiMessage == null) {
            //todo 异常描述需要修改一下
            throw new RuntimeException("未找到该对话或未找到该父消息!");
        }
    }


    //todo  取消时 怎么计算token  还需解决
    private void cancelChat() {

    }


}
