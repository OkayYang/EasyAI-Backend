package com.easyai.client.langchain4j.platform.openai.service;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.custom.mapper.ApiKeyCustomMapper;
import com.easyai.client.custom.mapper.EasyAiMessageErrorLogCustomMapper;
import com.easyai.client.custom.service.apikey.ApiKeyCustomService;
import com.easyai.client.langchain4j.platform.openai.domain.OpenAiErrorMessage;
import com.easyai.client.langchain4j.platform.openai.enums.OpenAiInnerErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:34
 */

@Service
public class OpenAiService {

    @Autowired
    public EasyAiMessageErrorLogCustomMapper easyAiMessageErrorLogCustomMapper;

    @Autowired
    public ApiKeyCustomService apiKeyCustomService;

    public void handleInnerError(String session_id,String message_id,String email,String model_name,ApiKey apiKey,OpenAiErrorMessage openAiErrorMessage){
        String code=openAiErrorMessage.getCode();
        String message = openAiErrorMessage.getMessage();
        String type = openAiErrorMessage.getType();
        int apiKey_id=apiKey.getId();
        if (OpenAiInnerErrorCode.Account_Deactivated.getValue().equals(code)) {
            apiKeyCustomService.setApiKeyOff(apiKey_id);
        } else if (OpenAiInnerErrorCode.Insufficient_Quota.getValue().equals(code)) {
            apiKeyCustomService.setApiKeyFinish(apiKey_id);
        }else if(OpenAiInnerErrorCode.Invalid_ApiKey.getValue().equals(code)){
            apiKeyCustomService.setApiKeyOff(apiKey_id);
        }
        easyAiMessageErrorLogCustomMapper.insertMessageErrorLog(session_id,message_id,email,code,type,message,apiKey.getApiKey(),model_name);

    }
}
