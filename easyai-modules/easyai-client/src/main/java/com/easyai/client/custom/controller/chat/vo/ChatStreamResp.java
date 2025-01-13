package com.easyai.client.custom.controller.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/23  21:01
 */

@Data
@AllArgsConstructor
public class ChatStreamResp<T> {
    String session_id;
    T content;
    String phase;
}
