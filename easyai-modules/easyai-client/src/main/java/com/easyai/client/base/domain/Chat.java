package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 用户会话对象 easyai_chat
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
public class Chat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会话id */
    private String sessionId;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 模型名 */
    @Excel(name = "模型名")
    private String modelName;

    /** 会话标题 */
    @Excel(name = "会话标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 会话状态 */
    @Excel(name = "会话状态")
    private String status;

    /** 记忆 */
    private String memory;

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
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setMemory(String memory)
    {
        this.memory = memory;
    }

    public String getMemory()
    {
        return memory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sessionId", getSessionId())
                .append("email", getEmail())
                .append("modelName", getModelName())
                .append("title", getTitle())
                .append("desc", getDesc())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("memory", getMemory())
                .toString();
    }
}
