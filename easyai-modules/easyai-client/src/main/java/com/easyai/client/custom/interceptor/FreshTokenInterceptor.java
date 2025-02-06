package com.easyai.client.custom.interceptor;

import com.easyai.client.custom.model.EasyAILoginUser;
import com.easyai.client.custom.service.EasyAITokenService;
import com.easyai.common.core.constant.SecurityConstants;
import com.easyai.common.core.context.SecurityContextHolder;
import com.easyai.common.core.utils.ServletUtils;
import com.easyai.common.core.utils.SpringUtils;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.security.service.TokenService;
import com.easyai.common.security.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * 普通用户请求头拦截器
 * 将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */
@Component
public class FreshTokenInterceptor implements AsyncHandlerInterceptor {
    public EasyAITokenService easyAITokenService = SpringUtils.getBean(EasyAITokenService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 设置用户基本信息到上下文
        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));

        // 获取并验证token
        String token = SecurityUtils.getToken();
        if (StringUtils.isNotEmpty(token)) {
            EasyAILoginUser loginUser = easyAITokenService.getLoginUser(token);
            if (StringUtils.isNotNull(loginUser)) {
                easyAITokenService.verifyToken(loginUser);
                SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 请求结束后清理线程变量
        SecurityContextHolder.remove();
    }
}