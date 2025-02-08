package com.easyai.client.langchain4j.platform.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:19
 */

public class OpenAiErrorMessage {
    String message;
    String type;
    String code;

    public OpenAiErrorMessage() {
    }

    public OpenAiErrorMessage(String message, String type, String code) {
        this.message = message;
        this.type = type;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "OpenAiErrorMessage{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
