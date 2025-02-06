package com.easyai.client.custom.controller.message.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author XuShiPing
 * @since 2024/12/28  20:54
 */
@Data
public class MessageListBody {
    private String content;
    private String messageId;
    private String modelName;
    private String parentId;
    private String role;
    private String sessionId;
    private Long token;
    private Long createAt;
}

