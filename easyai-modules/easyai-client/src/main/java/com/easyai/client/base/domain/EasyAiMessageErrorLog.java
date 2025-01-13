package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 消息异常日志对象 easyai_message_error_log
 *
 * @author xiaoshuaige
 * @date 2024-12-29
 */
public class EasyAiMessageErrorLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private int messageErrorLogId;

    /** 会话id */
    @Excel(name = "会话id")
    private String sessionId;

    /** 消息id */
    @Excel(name = "消息id")
    private String messageId;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 模型名 */
    @Excel(name = "模型名")
    private String modelName;

    /** 错误代码 */
    @Excel(name = "错误代码")
    private String code;

    /** 错误类型 */
    @Excel(name = "错误类型")
    private String type;

    /** 错误消息 */
    @Excel(name = "错误消息")
    private String message;

    /** 秘钥 */
    @Excel(name = "秘钥")
    private String apiKey;

    public void setMessageErrorLogId(int messageErrorLogId)
    {
        this.messageErrorLogId = messageErrorLogId;
    }

    public int getMessageErrorLogId()
    {
        return messageErrorLogId;
    }
    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getSessionId()
    {
        return sessionId;
    }
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageId()
    {
        return messageId;
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
    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageErrorLogId", getMessageErrorLogId())
            .append("sessionId", getSessionId())
            .append("messageId", getMessageId())
            .append("email", getEmail())
            .append("modelName", getModelName())
            .append("code", getCode())
            .append("type", getType())
            .append("message", getMessage())
            .append("apiKey", getApiKey())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
