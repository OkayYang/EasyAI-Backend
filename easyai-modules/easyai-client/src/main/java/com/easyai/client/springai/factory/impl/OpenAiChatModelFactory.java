package com.easyai.client.springai.factory.impl;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.springai.factory.SpringAiChatModelFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import static com.easyai.client.custom.constant.EasyAIConstants.PLATFORM_OPENAI;

@Service
public class OpenAiChatModelFactory implements SpringAiChatModelFactory {
    @Override
    public ChatModel createChatModel(ApiKey apiKey, com.easyai.client.base.domain.ChatModel modelConfig) {
        OpenAiApi openAiApi = new OpenAiApi(apiKey.getProxyUrl(),apiKey.getApiKey());
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model(modelConfig.getModelName())
                .build();
        return new OpenAiChatModel(openAiApi, openAiChatOptions);
    }

    @Override
    public String getPlatformName() {
        return PLATFORM_OPENAI;
    }
}
