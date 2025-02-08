package com.easyai.client.custom.controller.message.vo;

import lombok.Data;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2025/1/14  23:06
 */


public class MessageListRespBody {
    public String title;
    List<MessageListBody> messageListBodyList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MessageListBody> getMessageListBodyList() {
        return messageListBodyList;
    }

    public void setMessageListBodyList(List<MessageListBody> messageListBodyList) {
        this.messageListBodyList = messageListBodyList;
    }

    @Override
    public String toString() {
        return "MessageListRespBody{" +
                "title='" + title + '\'' +
                ", messageListBodyList=" + messageListBodyList +
                '}';
    }
}
