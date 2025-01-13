package com.easyai.client.custom.enums;

/**
 * @author XuShiPing
 * @since 2024/12/27  21:52
 */
public enum MessageStatusEnum {

    INNER_ERROR("调用错误", "远程调用内部错误");

    /**
     * 描述
     */
    private final String value;
    private final String description;

    // 构造方法
    MessageStatusEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
