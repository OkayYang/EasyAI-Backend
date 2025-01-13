package com.easyai.client.custom.enums;

/**
 * @author XuShiPing
 * @since 2024/12/27  21:52
 */

// gpt问答  分为 三个阶段
// 开始阶段  传输一些关键信息，防止用户提前暂停丢失父id消息
// 对话阶段  传输流式信息
// 总结阶段  传输结束信息，包括输入，输出token数
// 报错阶段  调用模型失败 则进入报错阶段 代替总结阶段
public enum MessageStreamResponsePhaseEnum {

    START("start","开始阶段"),
    CHAT("chat", "对话阶段"),
    CONCLUDE("conclude", "总结阶段"),
    ERROR("error", "报错阶段");


    /**
     * 描述
     */
    private final String value;
    private final String description;

    // 构造方法
    MessageStreamResponsePhaseEnum(String value, String description) {
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
