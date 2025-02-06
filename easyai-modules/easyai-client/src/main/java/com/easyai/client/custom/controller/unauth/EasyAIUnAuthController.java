package com.easyai.client.custom.controller.unauth;

import com.easyai.client.custom.controller.unauth.vo.*;
import com.easyai.client.custom.model.EasyAILoginUser;
import com.easyai.client.custom.service.EasyAIEmailSendService;
import com.easyai.client.custom.service.EasyAITokenService;
import com.easyai.client.custom.service.model.IModelCustomService;
import com.easyai.client.custom.service.user.IUserCustomService;
import com.easyai.common.core.domain.R;
import com.easyai.common.core.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "EasyAI用户端 - 认证")
@RestController
@RequestMapping("/easyai/auth")
@Validated
public class EasyAIUnAuthController {
    @Autowired
    private EasyAIEmailSendService easyAIEmailSendService;

    @Autowired
    private IUserCustomService easyAIUserCustomService;

    @Autowired
    private EasyAITokenService easyAITokenService;

    @Autowired
    private IModelCustomService modelCustomService;


    @Operation(summary = "获取邮箱验证吗")
    @PostMapping("/send-email-code")
    public AjaxResult sendEmailCode(@RequestBody @Valid SendEmailCodeReqBody sendEmailCodeReqBody) {
        easyAIEmailSendService.sendCode(sendEmailCodeReqBody);
        return AjaxResult.success("验证码发送成功");
    }

    @Operation(summary = "验证邮箱验证吗")
    @PostMapping("/verify-email-code")
    public AjaxResult verifyEmailCode(@RequestBody @Valid VerifyEmailCodeReqBody verifyEmailCodeReqBody) {
        easyAIEmailSendService.verifyCode(verifyEmailCodeReqBody);
        return AjaxResult.success("邮箱验证通过");
    }
    @Operation(summary = "忘记密码")
    @PostMapping("/reset-password")
    public AjaxResult resetPassword(@RequestBody @Valid ResetPasswordReqBody resetPasswordReqBody) {
        easyAIUserCustomService.resetPassword(resetPasswordReqBody);
        return AjaxResult.success("修改密码成功");

    }

    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public R<?> login(@RequestBody @Valid LoginReqBody loginReqBody) {
        // 用户登录
        EasyAILoginUser easyAILoginUser = easyAIUserCustomService.login(loginReqBody);
        // 获取登录token
        return R.ok(easyAITokenService.createToken(easyAILoginUser), "Login successfully.");
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody @Valid RegisterReqBody registerReqBody, @RequestParam(value = "invite", required = false) String inviteUid) {

        easyAIUserCustomService.register(registerReqBody, inviteUid);
        return AjaxResult.success("Register successfully.");

    }

    @Operation(summary = "接入模型列表")
    @GetMapping("/model/list")
    public AjaxResult modelList() {
        return AjaxResult.success(modelCustomService.getAllModels());

    }


}
