package com.easyai.client.custom.enums;

public enum RewardConfigStatusEnum {

    CONFIG_CLOSE("0", "未开启"),

    CONFIG_OPEN("1","开启");

    /**
     * 描述
     */
    private final String value;
    private final String description;

    // 构造方法
    RewardConfigStatusEnum(String value, String description) {
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
