package com.easyai.client.custom.service;

import com.easyai.client.custom.constant.EasyAIConstants;
import com.easyai.client.custom.controller.unauth.vo.SendEmailCodeReqBody;
import com.easyai.client.custom.controller.unauth.vo.VerifyEmailCodeReqBody;
import com.easyai.client.custom.enums.EmailCodeSceneEnum;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.custom.strategy.VerificationCodeStrategy;
import com.easyai.client.custom.strategy.factory.EmailCodeVerificationStrategy;
import com.easyai.client.custom.utils.RedisKeyUtils;
import com.easyai.client.base.domain.User;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.email.service.EmailService;
import com.easyai.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Component
public class EasyAIEmailSendService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserCustomMapper easyAIUserCustomMapper;
    @Autowired
    private EmailCodeVerificationStrategy emailCodeVerificationStrategy;


    // 生成验证码并发送到邮箱
    public void sendCode(SendEmailCodeReqBody sendEmailCodeReqBody) {
        String email = sendEmailCodeReqBody.getEmail();
        Integer scene = sendEmailCodeReqBody.getScene();  // 使用 scene 代替 type

        // 1. 获取对应的邮件场景枚举
//        EmailCodeSceneEnum emailSceneEnum = EmailCodeSceneEnum.getByScene(scene);
//        if (emailSceneEnum == null) {
//            throw new IllegalArgumentException("无效的邮件场景");  // 校验 scene 是否有效
//        }
        // 获取对应场景的前验策略
        emailCodeVerificationStrategy.validateBusinessRules(scene,email);
        // 2. 生成验证码
        String verificationCode = generateVerificationCode();

        // 3. 构造 Redis 的 Key，确保唯一性，避免不同类型的验证码冲突
        String redisKey = RedisKeyUtils.generateVerificationKey(email, EmailCodeSceneEnum.getByScene(scene).name());  // 使用枚举的名称构造 Redis key

        System.out.println("redisKey: " + redisKey);
        System.out.println("verificationCode:" + verificationCode);

        //4. 发送邮件
        if (!sendEmail(email, verificationCode)) {
            throw new ServiceException("Failed to send verification code.");
        }
        // 5. 将验证码存入 Redis，设置过期时间
        redisService.setCacheObject(redisKey, verificationCode, EasyAIConstants.EMAIL_CODE_EXPIRATION, TimeUnit.MINUTES);

    }

    public void verifyCode(VerifyEmailCodeReqBody verifyEmailCodeReqBody){
        String email = verifyEmailCodeReqBody.getEmail();
        Integer scene = verifyEmailCodeReqBody.getScene();
        String code = verifyEmailCodeReqBody.getCode();

        //构造redis key
        String redisKey = RedisKeyUtils.generateVerificationKey(email,EmailCodeSceneEnum.getByScene(scene).name());
        String realCode = redisService.getCacheObject(redisKey);
        if (StringUtils.isNull(realCode)||!StringUtils.equals(realCode,code)){
            throw new ServiceException("邮箱验证失败");
        }
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000); // 生成六位随机验证码
    }


    private boolean sendEmail(String email, String code) {
        try {
            String subject = "Your Verification Code";
            String text = "Your verification code is: " + code + "\nThis code will expire in 5 minutes.";
            SimpleMailMessage message = new SimpleMailMessage();
            return emailService.sendSimpleMail(email, subject, text);
        } catch (Exception e) {
            // 处理发送失败的情况，例如记录日志
            e.printStackTrace();
            return false;
        }
    }
}
