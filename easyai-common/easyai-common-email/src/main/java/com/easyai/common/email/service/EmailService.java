package com.easyai.common.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

/**
 * 发送邮件工具类 EmailService
 *
 * @author xuxiaoyang
 * @date 2024/10/15 16:52
 */
@Component
public class EmailService
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
    @Autowired
    private JavaMailSender mailSender;

    private final String FROM = "EasyGPT助手<1900516807@qq.com>";

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @return
     */

    public boolean sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        try {
            // 创建SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 邮件发送人
            message.setFrom(FROM);
            // 邮件接收人
            message.setTo(to);
            // 邮件主题
            message.setSubject(subject);
            // 邮件内容
            message.setText(content);
            // 发送邮件
            mailSender.send(message);
            return true; // 邮件发送成功
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * html邮件
     * @param to 收件人,多个时参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param subject 主题
     * @param content 内容
     */
    public boolean sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(FROM);
            //邮件接收人,设置多个收件人地址
            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressTo);
            //messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
            return true;
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
        }
        return false;
    }

    /**
     * 带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    public boolean sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(FROM);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
            return true;
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
        }
        return false;
    }
}
