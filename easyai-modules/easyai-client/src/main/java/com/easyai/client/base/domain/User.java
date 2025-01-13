package com.easyai.client.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.easyai.common.core.annotation.Excel;
import com.easyai.common.core.web.domain.BaseEntity;

/**
 * 用户对象 easyai_user
 *
 * @author xiaoyang
 * @date 2024-10-16
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 密码 */
    private String password;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 剩余算力 */
    @Excel(name = "剩余算力")
    private Long power;

    /** 聊天配置json */
    private String chatConfigJson;

    /** 聊天角色 json */
    private String chatRolesJson;

    /** AI模型 json */
    private String chatModelsJson;

    /** 帐号状态 */
    @Excel(name = "帐号状态")
    private String status;

    /** 会员状态 */
    @Excel(name = "会员状态")
    private String vip;

    /** 会员时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会员时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiredTime;

    /** 登录 IP */
    @Excel(name = "登录 IP")
    private String loginIp;

    /** 登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getNickname()
    {
        return nickname;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setPower(Long power)
    {
        this.power = power;
    }

    public Long getPower()
    {
        return power;
    }
    public void setChatConfigJson(String chatConfigJson)
    {
        this.chatConfigJson = chatConfigJson;
    }

    public String getChatConfigJson()
    {
        return chatConfigJson;
    }
    public void setChatRolesJson(String chatRolesJson)
    {
        this.chatRolesJson = chatRolesJson;
    }

    public String getChatRolesJson()
    {
        return chatRolesJson;
    }
    public void setChatModelsJson(String chatModelsJson)
    {
        this.chatModelsJson = chatModelsJson;
    }

    public String getChatModelsJson()
    {
        return chatModelsJson;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setVip(String vip)
    {
        this.vip = vip;
    }

    public String getVip()
    {
        return vip;
    }
    public void setExpiredTime(Date expiredTime)
    {
        this.expiredTime = expiredTime;
    }

    public Date getExpiredTime()
    {
        return expiredTime;
    }
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp()
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("nickname", getNickname())
            .append("password", getPassword())
            .append("avatar", getAvatar())
            .append("power", getPower())
            .append("chatConfigJson", getChatConfigJson())
            .append("chatRolesJson", getChatRolesJson())
            .append("chatModelsJson", getChatModelsJson())
            .append("status", getStatus())
            .append("vip", getVip())
            .append("expiredTime", getExpiredTime())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
