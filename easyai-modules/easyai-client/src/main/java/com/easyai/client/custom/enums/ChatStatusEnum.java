package com.easyai.client.custom.enums;

/**
 * @author XuShiPing
 * @since 2024/12/28  21:11
 */

//  十分普通的一个枚举，标志对话是否被删除
public enum ChatStatusEnum {

    EXIST(0),
    DELETE(1);

    private final int value;

    ChatStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
