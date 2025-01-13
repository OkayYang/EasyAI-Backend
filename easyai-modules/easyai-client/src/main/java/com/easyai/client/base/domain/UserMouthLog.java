package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 用户口令日志对象 easyai_user_mouth_log
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
public class UserMouthLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long mouthUserId;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 口令 */
    @Excel(name = "口令")
    private String category;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    public void setMouthUserId(Long mouthUserId)
    {
        this.mouthUserId = mouthUserId;
    }

    public Long getMouthUserId()
    {
        return mouthUserId;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mouthUserId", getMouthUserId())
            .append("email", getEmail())
            .append("category", getCategory())
            .append("reward", getReward())
            .append("createTime", getCreateTime())
            .toString();
    }
}
