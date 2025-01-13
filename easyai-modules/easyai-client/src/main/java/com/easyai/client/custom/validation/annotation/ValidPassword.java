package com.easyai.client.custom.validation.annotation;

import com.easyai.client.custom.validation.validator.EmailSceneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailSceneValidator.class)
public @interface ValidPassword {
    String message() default "密码格式不合规";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
