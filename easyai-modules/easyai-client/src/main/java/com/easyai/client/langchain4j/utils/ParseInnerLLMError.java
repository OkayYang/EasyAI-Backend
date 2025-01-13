package com.easyai.client.langchain4j.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.easyai.client.langchain4j.platform.openai.domain.OpenAiErrorMessage;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:26
 */
public class ParseInnerLLMError {
    /**
     * 解析OpenAi 内部错误信息
     * @param throwable
     */
    public static OpenAiErrorMessage parseOpenAiHttpExceptionMessage(Throwable throwable){
        JSONObject jsonObject = JSONUtil.parseObj(throwable.getMessage());
        JSONObject errorDetail = JSONUtil.parseObj(jsonObject.get("error").toString());
        String message=errorDetail.get("message").toString();
        String type=errorDetail.get("type").toString();
        String code=errorDetail.get("code").toString();
        return new OpenAiErrorMessage(message,type,code);
    }
}
