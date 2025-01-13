package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 密钥管理对象 easyai_api_key
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public class ApiKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private int id;

    /** 平台 */
    @Excel(name = "平台")
    private String platform;

    /** 名称 */
    @Excel(name = "名称")
    private String apiName;

    /** api_key */
    @Excel(name = "api_key")
    private String apiKey;

    /** 用途 */
    @Excel(name = "用途")
    private String purpose;

    /** 代理地址 */
    @Excel(name = "代理地址")
    private String proxyUrl;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String status;

    /** 是否用完 */
    @Excel(name = "是否用完")
    private String isFinished;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
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
    public void setApiName(String apiName)
    {
        this.apiName = apiName;
    }

    public String getApiName()
    {
        return apiName;
    }
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiKey()
    {
        return apiKey;
    }
    public void setPurpose(String purpose)
    {
        this.purpose = purpose;
    }

    public String getPurpose()
    {
        return purpose;
    }
    public void setProxyUrl(String proxyUrl)
    {
        this.proxyUrl = proxyUrl;
    }

    public String getProxyUrl()
    {
        return proxyUrl;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setIsFinished(String isFinished)
    {
        this.isFinished = isFinished;
    }

    public String getIsFinished()
    {
        return isFinished;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("platform", getPlatform())
                .append("apiName", getApiName())
                .append("apiKey", getApiKey())
                .append("purpose", getPurpose())
                .append("proxyUrl", getProxyUrl())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("isFinished", getIsFinished())
                .toString();
    }
}
