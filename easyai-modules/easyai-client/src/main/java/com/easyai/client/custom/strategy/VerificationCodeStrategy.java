package com.easyai.client.custom.strategy;

import com.easyai.client.custom.enums.EmailCodeSceneEnum;

public interface VerificationCodeStrategy {
    void validateBusinessRules(String email);
    EmailCodeSceneEnum getStrategyType();
}
