package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 奖励配置对象 easyai_reward_config
 *
 * @author xiaoshuaige
 * @date 2024-10-20
 */
public class RewardConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long rewardConfigId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    /** 描述 */
    @Excel(name = "描述")
    private String detail;

    /** 奖励状态 */
    @Excel(name = "奖励状态")
    private String status;

    public void setRewardConfigId(Long rewardConfigId)
    {
        this.rewardConfigId = rewardConfigId;
    }

    public Long getRewardConfigId()
    {
        return rewardConfigId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setReward(Long reward)
    {
        this.reward = reward;
    }

    public Long getReward()
    {
        return reward;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getDetail()
    {
        return detail;
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
                .append("rewardConfigId", getRewardConfigId())
                .append("name", getName())
                .append("reward", getReward())
                .append("detail", getDetail())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .toString();
    }
}
