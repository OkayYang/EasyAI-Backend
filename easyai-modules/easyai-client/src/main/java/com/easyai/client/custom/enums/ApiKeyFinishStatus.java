package com.easyai.client.custom.enums;

/**
 * @author XuShiPing
 * @since 2024/12/29  16:02
 */
public enum ApiKeyFinishStatus {

    FINISH(1),

    UNFINISHED(0);

    private int value;

    public int getValue() {
        return value;
    }
    private ApiKeyFinishStatus(int value) {
        this.value = value;
    }
}
