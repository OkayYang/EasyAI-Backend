package com.easyai.client.custom.strategy.impl;

import com.easyai.client.base.domain.User;
import com.easyai.client.custom.enums.EmailCodeSceneEnum;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.custom.strategy.VerificationCodeStrategy;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordVerificationStrategy implements VerificationCodeStrategy {
    @Autowired
    private UserCustomMapper easyAIUserCustomMapper;
    @Override
    public void validateBusinessRules(String email) {
        User user = easyAIUserCustomMapper.selectUserByUserName(email);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
    }

    @Override
    public EmailCodeSceneEnum getStrategyType() {
        return EmailCodeSceneEnum.USER_RESET_PASSWORD;
    }
}
