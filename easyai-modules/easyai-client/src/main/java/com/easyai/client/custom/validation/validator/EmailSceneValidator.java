package com.easyai.client.custom.validation.validator;

import com.easyai.client.custom.enums.EmailCodeSceneEnum;
import com.easyai.client.custom.validation.annotation.ValidEmailScene;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 实现验证器
public class EmailSceneValidator implements ConstraintValidator<ValidEmailScene, Integer> {

    @Override
    public void initialize(ValidEmailScene constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return EmailCodeSceneEnum.getByScene(value) != null;
    }
}
