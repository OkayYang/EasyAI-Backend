package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 消息对象 easyai_message
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
public class EasyAiMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    private String messageId;

    /** 会话id */
    @Excel(name = "会话id")
    private String sessionId;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 内容 */
    private String content;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 父消息id */
    @Excel(name = "父消息id")
    private String parentId;

    /** 模型名 */
    @Excel(name = "模型名")
    private String modelName;

    /** token */
    @Excel(name = "token")
    private Long token;

    /** 实际状态 */
    @Excel(name = "实际状态")
    private String contentStatus;

    /** 可视状态 */
    @Excel(name = "可视状态")
    private String visualContentStatus;

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageId()
    {
        return messageId;
    }
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getSessionId()
    {
        return sessionId;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getParentId()
    {
        return parentId;
    }
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }
    public void setToken(Long token)
    {
        this.token = token;
    }

    public Long getToken()
    {
        return token;
    }
    public void setContentStatus(String contentStatus)
    {
        this.contentStatus = contentStatus;
    }

    public String getContentStatus()
    {
        return contentStatus;
    }
    public void setVisualContentStatus(String visualContentStatus)
    {
        this.visualContentStatus = visualContentStatus;
    }

    public String getVisualContentStatus()
    {
        return visualContentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("messageId", getMessageId())
                .append("sessionId", getSessionId())
                .append("email", getEmail())
                .append("content", getContent())
                .append("role", getRole())
                .append("parentId", getParentId())
                .append("modelName", getModelName())
                .append("token", getToken())
                .append("contentStatus", getContentStatus())
                .append("visualContentStatus", getVisualContentStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
