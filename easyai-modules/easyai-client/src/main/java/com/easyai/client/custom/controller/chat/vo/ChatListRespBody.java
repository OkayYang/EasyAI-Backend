package com.easyai.client.custom.controller.chat.vo;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;

/**
 * ChatListRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-17T22:44:07.063691400+08:00[Asia/Shanghai]")

public class ChatListRespBody {
    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("title")
    private String title;

    @JsonProperty("desc")
    private String desc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonProperty("modelName")
    private String modelName;

    public ChatListRespBody sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * 会话id
     *
     * @return sessionId
     */

    @NotNull


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ChatListRespBody email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     */

    @NotNull


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ChatListRespBody title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     */

    @NotNull


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChatListRespBody desc(String desc) {
        this.desc = desc;
        return this;
    }

    /**
     * 描述
     *
     * @return desc
     */

    @NotNull


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ChatListRespBody createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * Get createTime
     *
     * @return createTime
     */

    @NotNull


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ChatListRespBody updateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    /**
     * Get updateTime
     *
     * @return updateTime
     */

    @NotNull


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ChatListRespBody modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    /**
     * Get modelName
     *
     * @return modelName
     */

    @NotNull


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChatListRespBody chatListRespBody = (ChatListRespBody) o;
        return Objects.equals(this.sessionId, chatListRespBody.sessionId) &&
                Objects.equals(this.email, chatListRespBody.email) &&
                Objects.equals(this.title, chatListRespBody.title) &&
                Objects.equals(this.desc, chatListRespBody.desc) &&
                Objects.equals(this.createTime, chatListRespBody.createTime) &&
                Objects.equals(this.updateTime, chatListRespBody.updateTime) &&
                Objects.equals(this.modelName, chatListRespBody.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, email, title, desc, createTime, updateTime, modelName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ChatListRespBody {\n");

        sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    desc: ").append(toIndentedString(desc)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
        sb.append("    modelName: ").append(toIndentedString(modelName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

