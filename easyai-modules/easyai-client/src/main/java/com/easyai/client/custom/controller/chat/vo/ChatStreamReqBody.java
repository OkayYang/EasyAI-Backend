package com.easyai.client.custom.controller.chat.vo;

import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/11/1  14:08
 */
@Data
public class ChatStreamReqBody {
    // 可以不携带
    String session_id;
    String userMessage;
    String modelName;
    // 可以不携带
    String parent_id;
}
