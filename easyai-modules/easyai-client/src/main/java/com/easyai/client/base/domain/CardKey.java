package com.easyai.client.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 卡密对象 easyai_card_key
 *
 * @author xiaoshuaige
 * @date 2024-10-18
 */
public class CardKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long cardKeyId;

    /** 卡密 */
    @Excel(name = "卡密")
    private String cardKey;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 卡密类型 */
    @Excel(name = "卡密类型")
    private String category;

    /** 0: 未使用 1:使用 */
    @Excel(name = "0: 未使用 1:使用")
    private String status;

    public void setCardKeyId(Long cardKeyId)
    {
        this.cardKeyId = cardKeyId;
    }

    public Long getCardKeyId()
    {
        return cardKeyId;
    }
    public void setCardKey(String cardKey)
    {
        this.cardKey = cardKey;
    }

    public String getCardKey()
    {
        return cardKey;
    }
    public void setReward(Long reward)
    {
        this.reward = reward;
    }

    public Long getReward()
    {
        return reward;
    }
    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
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
            .append("cardKeyId", getCardKeyId())
            .append("cardKey", getCardKey())
            .append("reward", getReward())
            .append("createTime", getCreateTime())
            .append("expireTime", getExpireTime())
            .append("category", getCategory())
            .append("status", getStatus())
            .toString();
    }
}
