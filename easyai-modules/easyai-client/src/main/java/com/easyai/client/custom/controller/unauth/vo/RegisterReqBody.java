package com.easyai.client.custom.controller.unauth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

/**
 * RegisterReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-15T15:41:55.271058+08:00[Asia/Shanghai]")

public class RegisterReqBody {
  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  @JsonProperty("code")
  private String code;

  public RegisterReqBody username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */

  @NotNull
  @Pattern(regexp="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "邮箱格式不正确")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public RegisterReqBody password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */

  @NotNull
  @Pattern(
          regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$",
          message = "密码格式不正确，必须包含字母和数字，且长度大于6"
  )
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegisterReqBody code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */

  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterReqBody registerReqBody = (RegisterReqBody) o;
    return Objects.equals(this.username, registerReqBody.username) &&
        Objects.equals(this.password, registerReqBody.password) &&
        Objects.equals(this.code, registerReqBody.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, code);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterReqBody {\n");

    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
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

