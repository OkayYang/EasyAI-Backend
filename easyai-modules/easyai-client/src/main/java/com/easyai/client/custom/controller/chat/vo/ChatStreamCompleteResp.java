package com.easyai.client.custom.controller.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/27  22:23
 */

@Data
@AllArgsConstructor
public class ChatStreamCompleteResp {
    public String ai_message_id;
    public int inputToken;
    public int outputToken;
    public String finishReason;
}
