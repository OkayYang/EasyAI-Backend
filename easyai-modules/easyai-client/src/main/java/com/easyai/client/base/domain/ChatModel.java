package com.easyai.client.base.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * AI 模型对象 easyai_chat_model
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public class ChatModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模型id */
    private Long id;

    /** 平台 */
    @Excel(name = "平台")
    private String platform;

    /** 模型名称 */
    @Excel(name = "模型名称")
    private String modelName;

    /** 模型版本 */
    @Excel(name = "模型版本")
    private String version;

    /** 创意度 */
    @Excel(name = "创意度")
    private BigDecimal temperature;

    /** 最大token数 */
    @Excel(name = "最大token数")
    private Long maxTokens;

    /** 最大上下文长度 */
    @Excel(name = "最大上下文长度")
    private Long maxContext;

    /** 算力点数 */
    @Excel(name = "算力点数")
    private int price;

    /** 排序优先级 */
    @Excel(name = "排序优先级")
    private Integer orderNum;

    /** 模型状态 */
    @Excel(name = "模型状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public String getPlatform()
    {
        return platform;
    }
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }
    public void setTemperature(BigDecimal temperature)
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature()
    {
        return temperature;
    }
    public void setMaxTokens(Long maxTokens)
    {
        this.maxTokens = maxTokens;
    }

    public Long getMaxTokens()
    {
        return maxTokens;
    }
    public void setMaxContext(Long maxContext)
    {
        this.maxContext = maxContext;
    }

    public Long getMaxContext()
    {
        return maxContext;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }
    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("platform", getPlatform())
                .append("modelName", getModelName())
                .append("version", getVersion())
                .append("temperature", getTemperature())
                .append("maxTokens", getMaxTokens())
                .append("maxContext", getMaxContext())
                .append("price", getPrice())
                .append("orderNum", getOrderNum())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
