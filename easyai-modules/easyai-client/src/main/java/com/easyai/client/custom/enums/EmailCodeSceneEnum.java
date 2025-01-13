package com.easyai.client.custom.enums;

public enum EmailCodeSceneEnum {

    USER_REGISTER(1, "user-email-register", "用户注册"),
    USER_LOGIN(2, "user-email-login", "用户登录"),
    USER_RESET_PASSWORD(3, "user-email-reset-password", "用户重置密码"),
    USER_UPDATE_EMAIL(4, "user-email-update", "用户更新邮箱");

    /**
     * 验证场景的编号
     */
    private final Integer scene;

    /**
     * 邮件模板编码
     */
    private final String templateCode;

    /**
     * 描述
     */
    private final String description;

    // 构造方法
    EmailCodeSceneEnum(Integer scene, String templateCode, String description) {
        this.scene = scene;
        this.templateCode = templateCode;
        this.description = description;
    }

    // Getter 方法
    public Integer getScene() {
        return scene;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据场景编号获取对应的枚举
     */
    public static EmailCodeSceneEnum getByScene(Integer scene) {
        for (EmailCodeSceneEnum emailScene : values()) {
            if (emailScene.getScene().equals(scene)) {
                return emailScene;
            }
        }
        return null; // 找不到对应场景时返回 null
    }
}
