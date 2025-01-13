package com.easyai.client.custom.controller.unauth.vo;

import com.easyai.client.custom.validation.annotation.ValidEmailScene;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

/**
 * ResetPasswordReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-1-7T15:41:55.271058+08:00[Asia/Shanghai]")

public class ResetPasswordReqBody {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;  // 场景编号

    @JsonProperty("code")
    private String code;

    public ResetPasswordReqBody email(String email) {
        this.email = email;
        return this;
    }

    /**
     * 邮箱
     * @return email
     */

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "密码不能为空")
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

    @NotNull(message = "验证码不能为空")
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
        ResetPasswordReqBody that = (ResetPasswordReqBody) o;
        return Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.code,that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password,code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResetPasswordReqBody {\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
