package com.easyai.client.custom.controller.message.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author XuShiPing
 * @since 2024/12/28  20:54
 */

public class MessageListBody {
    private String content;
    private String thinkingContent;
    private String messageId;
    private String modelName;
    private String parentId;
    private String role;
    private String sessionId;
    private Long token;
    private Long createAt;

    public String getThinkingContent() {
        return thinkingContent;
    }

    public void setThinkingContent(String thinkingContent) {
        this.thinkingContent = thinkingContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "MessageListBody{" +
                "content='" + content + '\'' +
                ", messageId='" + messageId + '\'' +
                ", modelName='" + modelName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", role='" + role + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", token=" + token +
                ", createAt=" + createAt +
                '}';
    }
}

