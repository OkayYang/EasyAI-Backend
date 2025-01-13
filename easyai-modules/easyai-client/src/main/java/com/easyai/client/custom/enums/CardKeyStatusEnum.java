package com.easyai.client.custom.enums;

public enum CardKeyStatusEnum {

    Card_UNUSED("0", "未使用"),

    Card_USED("1","使用");

    /**
     * 描述
     */
    private final String value;
    private final String description;

    // 构造方法
    CardKeyStatusEnum(String value, String description) {
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
