package com.easyai.client.custom.strategy.factory;

import com.easyai.client.custom.enums.EmailCodeSceneEnum;
import com.easyai.client.custom.strategy.VerificationCodeStrategy;
import com.easyai.client.custom.strategy.impl.RegisterVerificationStrategy;
import com.easyai.client.custom.strategy.impl.ResetPasswordVerificationStrategy;
import com.easyai.common.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmailCodeVerificationStrategy {
    private final Map<Integer, VerificationCodeStrategy> strategies = new HashMap<>();

    // 使用构造器注入所有策略实现
    @Autowired
    public EmailCodeVerificationStrategy(List<VerificationCodeStrategy> strategyList) {
        // 通过策略类名来判断类型，将策略实现注册到Map中
        strategyList.forEach(strategy -> {
            strategies.put(strategy.getStrategyType().getScene(),strategy);
        });
    }
    public void validateBusinessRules(Integer scene, String email) {
        VerificationCodeStrategy strategy = strategies.get(scene);
        if (strategy == null) {
            throw new ServiceException("未找到对应的验证策略");
        }
        strategy.validateBusinessRules(email);
    }

}