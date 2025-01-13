package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 消息日志对象 easyai_message_log
 *
 * @author xiaoshuaiage
 * @date 2024-12-25
 */
public class EasyAiMessageLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息日志id */
    private String messageLogId;

    /** 会话id */
    @Excel(name = "会话id")
    private String sessionId;

    /** 消息id */
    @Excel(name = "消息id")
    private String messageId;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 输入token */
    @Excel(name = "输入token")
    private Long inputToken;

    /** 输出token */
    @Excel(name = "输出token")
    private Long outputToken;

    /** 合计token */
    @Excel(name = "合计token")
    private Long totalToken;

    /** 模型名 */
    @Excel(name = "模型名")
    private String modelName;

    public void setMessageLogId(String messageLogId)
    {
        this.messageLogId = messageLogId;
    }

    public String getMessageLogId()
    {
        return messageLogId;
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
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setInputToken(Long inputToken)
    {
        this.inputToken = inputToken;
    }

    public Long getInputToken()
    {
        return inputToken;
    }
    public void setOutputToken(Long outputToken)
    {
        this.outputToken = outputToken;
    }

    public Long getOutputToken()
    {
        return outputToken;
    }
    public void setTotalToken(Long totalToken)
    {
        this.totalToken = totalToken;
    }

    public Long getTotalToken()
    {
        return totalToken;
    }
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("messageLogId", getMessageLogId())
                .append("sessionId", getSessionId())
                .append("messageId", getMessageId())
                .append("email", getEmail())
                .append("content", getContent())
                .append("inputToken", getInputToken())
                .append("outputToken", getOutputToken())
                .append("totalToken", getTotalToken())
                .append("modelName", getModelName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
