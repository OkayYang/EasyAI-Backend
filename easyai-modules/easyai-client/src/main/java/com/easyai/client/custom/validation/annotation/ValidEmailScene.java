package com.easyai.client.custom.validation.annotation;

import com.easyai.client.custom.validation.validator.EmailSceneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailSceneValidator.class)
public @interface ValidEmailScene {
    String message() default "Invalid email scene value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
