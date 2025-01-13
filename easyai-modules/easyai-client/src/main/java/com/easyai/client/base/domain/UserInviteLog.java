package com.easyai.client.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 用户邀请日志对象 easyai_user_invite_log
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
public class UserInviteLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long inviteId;

    /** 邀请人邮箱 */
    @Excel(name = "邀请人邮箱")
    private String inviteEmail;

    /** 被邀请人邮箱 */
    @Excel(name = "被邀请人邮箱")
    private String invitedEmail;

    /** 算力奖励 */
    @Excel(name = "算力奖励")
    private Long reward;

    public void setInviteId(Long inviteId)
    {
        this.inviteId = inviteId;
    }

    public Long getInviteId()
    {
        return inviteId;
    }
    public void setInviteEmail(String inviteEmail)
    {
        this.inviteEmail = inviteEmail;
    }

    public String getInviteEmail()
    {
        return inviteEmail;
    }
    public void setInvitedEmail(String invitedEmail)
    {
        this.invitedEmail = invitedEmail;
    }

    public String getInvitedEmail()
    {
        return invitedEmail;
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
                .append("inviteId", getInviteId())
                .append("inviteEmail", getInviteEmail())
                .append("invitedEmail", getInvitedEmail())
                .append("reward", getReward())
                .append("createTime", getCreateTime())
                .toString();
    }
}
