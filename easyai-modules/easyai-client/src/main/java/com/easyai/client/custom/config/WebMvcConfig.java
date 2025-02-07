package com.easyai.client.custom.config;

import com.easyai.client.custom.interceptor.FreshTokenInterceptor;
import com.easyai.common.security.interceptor.HeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FreshTokenInterceptor freshTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册用户请求头拦截器
        registry.addInterceptor(freshTokenInterceptor)
                // 拦截所有请求
                .addPathPatterns("/easyai/conversation/**")
                .order(-9);
    }
}

