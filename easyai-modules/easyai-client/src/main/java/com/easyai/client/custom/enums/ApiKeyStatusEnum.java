package com.easyai.client.custom.enums;

/**
 * @author XuShiPing
 * @since 2024/12/29  16:01
 */
public enum ApiKeyStatusEnum {
    OFF(0),

    OPEN(1);

    int value;

    ApiKeyStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
