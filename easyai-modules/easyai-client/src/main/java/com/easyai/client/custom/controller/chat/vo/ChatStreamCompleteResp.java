package com.easyai.client.custom.controller.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/27  22:23
 */


public class ChatStreamCompleteResp {
    public String ai_message_id;
    public Long inputToken;
    public Long outputToken;
    public String finishReason;

    public ChatStreamCompleteResp() {
    }

    public ChatStreamCompleteResp(String ai_message_id, Long inputToken, Long outputToken, String finishReason) {
        this.ai_message_id = ai_message_id;
        this.inputToken = inputToken;
        this.outputToken = outputToken;
        this.finishReason = finishReason;
    }

    public String getAi_message_id() {
        return ai_message_id;
    }

    public void setAi_message_id(String ai_message_id) {
        this.ai_message_id = ai_message_id;
    }

    public Long getInputToken() {
        return inputToken;
    }

    public void setInputToken(Long inputToken) {
        this.inputToken = inputToken;
    }

    public Long getOutputToken() {
        return outputToken;
    }

    public void setOutputToken(Long outputToken) {
        this.outputToken = outputToken;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    @Override
    public String toString() {
        return "ChatStreamCompleteResp{" +
                "ai_message_id='" + ai_message_id + '\'' +
                ", inputToken=" + inputToken +
                ", outputToken=" + outputToken +
                ", finishReason='" + finishReason + '\'' +
                '}';
    }
}
