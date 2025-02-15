package com.easyai.client.custom.service.chat;

import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.custom.controller.chat.vo.ChatListRespBody;
import com.easyai.client.custom.controller.chat.vo.ChatStreamReqBody;
import com.easyai.client.custom.controller.chat.vo.ChatStreamResp;
import com.easyai.client.custom.controller.chat.vo.EditTitleReqBody;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/17  18:58
 */
public interface IChatCustomService {

    /**
     * 按时间排序 返回对话列表
     * @return
     */
    List<ChatListRespBody> listAllChat();

    Flux<ChatStreamResp<?>> chat(ChatStreamReqBody chatStreamReqBody);
    Flux<ChatStreamResp<?>> springAiChat(ChatStreamReqBody chatStreamReqBody);

    void deleteChat(String session_id);

    int updateChatTitle(EditTitleReqBody editTitleReqBody);

}
