package com.easyai.client.custom.controller.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/23  21:01
 */


public class ChatStreamResp<T> {
    String session_id;
    T content;
    String phase;

    public ChatStreamResp() {
    }

    public ChatStreamResp(T content, String phase) {
        this.content = content;
        this.phase = phase;
    }

    public ChatStreamResp(String session_id, T content, String phase) {
        this.session_id = session_id;
        this.content = content;
        this.phase = phase;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "ChatStreamResp{" +
                "session_id='" + session_id + '\'' +
                ", content=" + content +
                ", phase='" + phase + '\'' +
                '}';
    }
}
