package com.easyai.client.custom.service.message;

import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.custom.controller.message.vo.MessageListBody;
import com.easyai.client.custom.controller.message.vo.MessageListRespBody;
import com.easyai.client.custom.enums.ChatStatusEnum;
import com.easyai.client.custom.mapper.ChatCustomMapper;
import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/28  20:59
 */

@Service
public class MessageCustomService implements IMessageCustomService {

    @Autowired
    private EasyAiMessageCustomMapper easyAiMessageCustomMapper;

    @Autowired
    private ChatCustomMapper chatCustomMapper;


    @Override
    public MessageListRespBody getMessageList(String session_id) {
        String email = SecurityUtils.getUsername();
        Chat chatBySessionId = chatCustomMapper.getChatBySessionId(email, session_id, ChatStatusEnum.EXIST.getValue());
        if (chatBySessionId == null) {
            throw new RuntimeException("未查询到该对话，或该对话已被删除!");
        }

        List<EasyAiMessage> messageListBySessionId = easyAiMessageCustomMapper.getMessageListBySessionId(session_id);
        List<MessageListBody> messageListRespBodies = new ArrayList<>();

        for (EasyAiMessage message : messageListBySessionId) {
            MessageListBody respBody = new MessageListBody();
            BeanUtils.copyProperties(message, respBody);
            messageListRespBodies.add(respBody);
        }
        MessageListRespBody messageListRespBody=new MessageListRespBody();
        messageListRespBody.setTitle(chatBySessionId.getTitle());
        messageListRespBody.setMessageListBodyList(messageListRespBodies);
        return messageListRespBody;
    }
}
