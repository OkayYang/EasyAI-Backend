package com.easyai.client.custom.controller.chat.vo;

public class ResponseMessage {
    private String text;
    private String type;

    public ResponseMessage() {
    }

    public ResponseMessage(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
