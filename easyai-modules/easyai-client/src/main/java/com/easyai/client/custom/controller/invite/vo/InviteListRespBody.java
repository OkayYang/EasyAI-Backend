package com.easyai.client.custom.controller.invite.vo;

import java.util.Date;
import java.util.Objects;

import com.easyai.common.sensitive.annotation.Sensitive;
import com.easyai.common.sensitive.enums.DesensitizedType;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.*;

/**
 * InviteListRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-22T19:44:07.608525800+08:00[Asia/Shanghai]")

public class InviteListRespBody   {
  @JsonProperty("invited_email")
  @Sensitive(desensitizedType = DesensitizedType.EMAIL)
  private String invitedEmail;

  @JsonProperty("reward")
  private Long reward;

  @JsonProperty("create_time")
  private Date createTime;

  public InviteListRespBody invitedEmail(String invitedEmail) {
    this.invitedEmail = invitedEmail;
    return this;
  }

  /**
   * 被邀请人邮箱
   * @return invitedEmail
  */

  @NotNull


  public String getInvitedEmail() {
    return invitedEmail;
  }

  public void setInvitedEmail(String invitedEmail) {
    this.invitedEmail = invitedEmail;
  }

  public InviteListRespBody reward(Long reward) {
    this.reward = reward;
    return this;
  }

  /**
   * 算力奖励
   * @return reward
  */

  @NotNull


  public Long getReward() {
    return reward;
  }

  public void setReward(Long reward) {
    this.reward = reward;
  }

  public InviteListRespBody createTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 邀请时间
   * @return createTime
  */

  @NotNull


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InviteListRespBody inviteListRespBody = (InviteListRespBody) o;
    return Objects.equals(this.invitedEmail, inviteListRespBody.invitedEmail) &&
        Objects.equals(this.reward, inviteListRespBody.reward) &&
        Objects.equals(this.createTime, inviteListRespBody.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invitedEmail, reward, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InviteListRespBody {\n");

    sb.append("    invitedEmail: ").append(toIndentedString(invitedEmail)).append("\n");
    sb.append("    reward: ").append(toIndentedString(reward)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

