package com.easyai.client.custom.controller.user;

import com.easyai.client.custom.controller.user.vo.UserInfoRespBody;
import com.easyai.client.custom.model.EasyAILoginUser;
import com.easyai.client.custom.service.EasyAITokenService;
import com.easyai.client.custom.service.user.IUserCustomService;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.security.auth.AuthUtil;
import com.easyai.common.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "EasyAI用户端 - 用户")
@RestController
@RequestMapping("/easyai/user")
@Validated
public class EasyAIUserController {

    @Autowired
    private EasyAITokenService easyAITokenService;
    @Autowired
    private IUserCustomService easyAIUserCustomService;


    @DeleteMapping("/logout")
    public AjaxResult gptLogout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
        }
        return AjaxResult.success("Logout successfully.");
    }

    @PostMapping("/refresh")
    public AjaxResult refresh(HttpServletRequest request) {
        EasyAILoginUser loginUser = easyAITokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            easyAITokenService.refreshToken(loginUser);
            return AjaxResult.success("successfully refresh token.");
        }
        return AjaxResult.error("refresh token is empty.");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getUserInfo(HttpServletRequest request) {
        EasyAILoginUser loginUser = easyAITokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            UserInfoRespBody userInfoRespBody = easyAIUserCustomService.getUserInfo(loginUser.getUserid());
            return AjaxResult.success(userInfoRespBody);

        }
        //未登陆
        return AjaxResult.error("Please login first.");
    }



}
