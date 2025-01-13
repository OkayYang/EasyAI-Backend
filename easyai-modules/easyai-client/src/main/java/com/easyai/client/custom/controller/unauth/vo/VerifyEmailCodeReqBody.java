package com.easyai.client.custom.controller.unauth.vo;

import com.easyai.client.custom.enums.EmailCodeSceneEnum;

import com.easyai.client.custom.validation.annotation.ValidEmailScene;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

/**
 * VerifyEmailCodeReqBody
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-1-6T15:41:55.271058+08:00[Asia/Shanghai]")

public class VerifyEmailCodeReqBody {

    @JsonProperty("email")
    private String email;

    @JsonProperty("scene")
    @ValidEmailScene
    private Integer scene;  // 场景编号

    @JsonProperty("code")
    private String code;

    public VerifyEmailCodeReqBody email(String email) {
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

    public VerifyEmailCodeReqBody scene(Integer scene) {
        this.scene = scene;
        return this;
    }

    /**
     * 邮件场景
     * @return scene
     */
    // 使用 InEnum 验证场景编号

    @NotNull(message = "邮件场景不能为空")
    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
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
        VerifyEmailCodeReqBody that = (VerifyEmailCodeReqBody) o;
        return Objects.equals(this.email, that.email) &&
                Objects.equals(this.scene, that.scene) &&
                Objects.equals(this.code,that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, scene,code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerifyEmailCodeReqBody {\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    scene: ").append(toIndentedString(scene)).append("\n");
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
