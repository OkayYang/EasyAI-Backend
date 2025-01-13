package com.easyai.client.custom.utils;

public class RedisKeyUtils {

    // 通用的构造 Redis key 方法
    private static String buildKey(String prefix, String... components) {
        StringBuilder keyBuilder = new StringBuilder(prefix);
        for (String component : components) {
            keyBuilder.append(":").append(component);
        }
        return keyBuilder.toString();
    }

    // 生成用于验证码的 Redis Key
    public static String generateVerificationKey(String identifier, String type) {
        return buildKey("verification", identifier, type);
    }

}
