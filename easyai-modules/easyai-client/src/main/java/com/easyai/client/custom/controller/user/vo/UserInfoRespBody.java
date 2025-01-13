package com.easyai.client.custom.controller.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * UserInfoRespBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-15T15:41:55.271058+08:00[Asia/Shanghai]")

public class UserInfoRespBody {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("nickname")
  private String nickname;

  @JsonProperty("avatar")
  private String avatar;

  @JsonProperty("power")
  private Long power;

  @JsonProperty("loginIp")
  private String loginIp;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date loginDate;

  public UserInfoRespBody id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */

  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserInfoRespBody username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */

  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserInfoRespBody nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  /**
   * Get nickname
   * @return nickname
  */

  @NotNull


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public UserInfoRespBody avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  /**
   * Get avatar
   * @return avatar
  */

  @NotNull


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public UserInfoRespBody power(Long power) {
    this.power = power;
    return this;
  }

  /**
   * Get power
   * @return power
  */

  @NotNull


  public Long getPower() {
    return power;
  }

  public void setPower(Long power) {
    this.power = power;
  }

  public UserInfoRespBody loginIp(String loginIp) {
    this.loginIp = loginIp;
    return this;
  }

  /**
   * Get loginIp
   * @return loginIp
  */

  @NotNull


  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public UserInfoRespBody loginDate(Date loginDate) {
    this.loginDate = loginDate;
    return this;
  }

  /**
   * Get loginDate
   * @return loginDate
  */

  @NotNull


  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfoRespBody userInfoRespBody = (UserInfoRespBody) o;
    return Objects.equals(this.id, userInfoRespBody.id) &&
        Objects.equals(this.username, userInfoRespBody.username) &&
        Objects.equals(this.nickname, userInfoRespBody.nickname) &&
        Objects.equals(this.avatar, userInfoRespBody.avatar) &&
        Objects.equals(this.power, userInfoRespBody.power) &&
        Objects.equals(this.loginIp, userInfoRespBody.loginIp) &&
        Objects.equals(this.loginDate, userInfoRespBody.loginDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, nickname, avatar, power, loginIp, loginDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfoRespBody {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    nickname: ").append(toIndentedString(nickname)).append("\n");
    sb.append("    avatar: ").append(toIndentedString(avatar)).append("\n");
    sb.append("    power: ").append(toIndentedString(power)).append("\n");
    sb.append("    loginIp: ").append(toIndentedString(loginIp)).append("\n");
    sb.append("    loginDate: ").append(toIndentedString(loginDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

