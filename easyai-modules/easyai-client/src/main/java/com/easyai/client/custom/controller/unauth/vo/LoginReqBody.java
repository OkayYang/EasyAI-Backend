package com.easyai.client.custom.controller.unauth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 * LoginReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-15T15:41:55.271058+08:00[Asia/Shanghai]")

public class LoginReqBody {
  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  public LoginReqBody username(String username) {
    this.username = username;
    return this;
  }

  /**
   * 账户
   * @return username
  */

  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginReqBody password(String password) {
    this.password = password;
    return this;
  }

  /**
   * 密码
   * @return password
  */

  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginReqBody loginReqBody = (LoginReqBody) o;
    return Objects.equals(this.username, loginReqBody.username) &&
        Objects.equals(this.password, loginReqBody.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginReqBody {\n");

    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

