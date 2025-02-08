package com.easyai.client.custom.controller.chat.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author XuShiPing
 * @since 2024/12/28  12:15
 */

@JsonInclude(JsonInclude.Include.NON_NULL) // 只序列化非 null 的字段
public class ChatStreamStartResp {
    String parent_id;
    String user_message_id;
    String ai_message_id;
    String model_name;
    String title;
    Long createTime;

    public ChatStreamStartResp() {
    }

    public ChatStreamStartResp(String parent_id, String user_message_id, String ai_message_id, String model_name, String title, Long createTime) {
        this.parent_id = parent_id;
        this.user_message_id = user_message_id;
        this.ai_message_id = ai_message_id;
        this.model_name = model_name;
        this.title = title;
        this.createTime = createTime;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getUser_message_id() {
        return user_message_id;
    }

    public void setUser_message_id(String user_message_id) {
        this.user_message_id = user_message_id;
    }

    public String getAi_message_id() {
        return ai_message_id;
    }

    public void setAi_message_id(String ai_message_id) {
        this.ai_message_id = ai_message_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChatStreamStartResp{" +
                "parent_id='" + parent_id + '\'' +
                ", user_message_id='" + user_message_id + '\'' +
                ", ai_message_id='" + ai_message_id + '\'' +
                ", model_name='" + model_name + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
