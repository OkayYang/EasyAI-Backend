package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 用户卡密日志对象 easyai_user_card_log
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
public class UserCardLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long userCardId;

    /** 卡密类型 */
    @Excel(name = "卡密类型")
    private String category;

    /** 使用者邮箱 */
    @Excel(name = "使用者邮箱")
    private String email;

    /** 卡密 */
    @Excel(name = "卡密")
    private String cardKey;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    public void setUserCardId(Long userCardId)
    {
        this.userCardId = userCardId;
    }

    public Long getUserCardId()
    {
        return userCardId;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userCardId", getUserCardId())
            .append("category", getCategory())
            .append("email", getEmail())
            .append("cardKey", getCardKey())
            .append("reward", getReward())
            .append("createTime", getCreateTime())
            .toString();
    }
}