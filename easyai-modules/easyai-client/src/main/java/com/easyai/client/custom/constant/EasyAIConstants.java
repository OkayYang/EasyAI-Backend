package com.easyai.client.custom.constant;

public class EasyAIConstants {
    /**
     * 邮箱验证码有效期（分钟）
     */
    public static final long EMAIL_CODE_EXPIRATION = 5;

    /**
     * token类型
     */
    public static final String NAME = "EasyAI";

    /**
     *  邀请奖励 配置 类型
     */
    public static final String INVITE_REWARD_TAG = "invite_reward";

    /**
     *  注册奖励 配置 类型
     */
    public static final String REGISTER_REWARD_TAG = "register_reward";

    /**
     *  注册奖励 配置 类型
     */
    public static final String CLOCK_IN_REWARD_TAG = "clock_in_reward";


    /**
     *  平台常量
     */
    public static final String PLATFORM_OPENAI = "OpenAI";
    public static final String PLATFORM_OLLAMA = "ollama";


    /**
     *  对话存储 角色描述 常量 easyai_message表中role字段
     */
    public static final String EASYAI_USER = "User";

    public static final String EASYAI_AI = "AI";

    /**
     * 记忆最大值
     */
    public static final int MEMORY_SIZE = 10;



}
