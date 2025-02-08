package com.easyai.client.custom.controller.chat.vo;

import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/11/1  14:08
 */

public class ChatStreamReqBody {
    // 可以不携带
    String session_id;
    String userMessage;
    String modelName;
    // 可以不携带
    String parent_id;

    public ChatStreamReqBody() {
    }

    public ChatStreamReqBody(String session_id, String userMessage, String modelName, String parent_id) {
        this.session_id = session_id;
        this.userMessage = userMessage;
        this.modelName = modelName;
        this.parent_id = parent_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "ChatStreamReqBody{" +
                "session_id='" + session_id + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", modelName='" + modelName + '\'' +
                ", parent_id='" + parent_id + '\'' +
                '}';
    }
}
