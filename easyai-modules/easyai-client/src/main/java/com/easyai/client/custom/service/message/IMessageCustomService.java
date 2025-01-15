package com.easyai.client.custom.service.message;

import com.easyai.client.custom.controller.message.vo.MessageListBody;
import com.easyai.client.custom.controller.message.vo.MessageListRespBody;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/28  21:00
 */
public interface IMessageCustomService {

    MessageListRespBody getMessageList(String session_id);
}
