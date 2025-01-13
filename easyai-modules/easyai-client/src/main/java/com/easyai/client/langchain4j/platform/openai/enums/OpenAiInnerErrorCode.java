package com.easyai.client.langchain4j.platform.openai.enums;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:43
 */

// 目前只知道这两个报错，后序有待添加
public enum OpenAiInnerErrorCode {
    Insufficient_Quota("insufficient_quota"),

    Account_Deactivated("account_deactivated"),

    Invalid_ApiKey("invalid_api_key");

    /**
     * 描述
     */
    private final String value;

    OpenAiInnerErrorCode(String value) {
        this.value = value;;
    }

    public String getValue() {
        return value;
    }
}
