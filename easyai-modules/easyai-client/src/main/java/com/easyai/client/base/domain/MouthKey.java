package com.easyai.client.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 口令管理对象 easyai_mouth_key
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
public class MouthKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long mouthKeyId;

    /** 口令 */
    @Excel(name = "口令")
    private String category;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    public void setMouthKeyId(Long mouthKeyId)
    {
        this.mouthKeyId = mouthKeyId;
    }

    public Long getMouthKeyId()
    {
        return mouthKeyId;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mouthKeyId", getMouthKeyId())
            .append("category", getCategory())
            .append("reward", getReward())
            .append("createTime", getCreateTime())
            .append("expireTime", getExpireTime())
            .toString();
    }
}
