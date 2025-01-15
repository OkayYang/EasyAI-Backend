package com.easyai.client.custom.controller.message.vo;

import lombok.Data;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2025/1/14  23:06
 */

@Data
public class MessageListRespBody {
    public String title;
    List<MessageListBody> messageListBodyList;
}
