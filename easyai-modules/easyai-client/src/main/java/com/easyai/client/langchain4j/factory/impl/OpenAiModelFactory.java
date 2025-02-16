package com.easyai.client.langchain4j.factory.impl;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiChatModel;
import com.easyai.client.langchain4j.factory.ModelFactory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.stereotype.Service;

import static com.easyai.client.custom.constant.EasyAIConstants.PLATFORM_OPENAI;

@Service
public class OpenAiModelFactory implements ModelFactory {
    @Override
    public StreamingChatLanguageModel createModel(ApiKey apiKey, EasyAiChatModel modelConfig) {
        return OpenAiStreamingChatModel.builder()
                .apiKey(apiKey.getApiKey())
                .modelName(modelConfig.getModelName())
                .build();
    }

    @Override
    public String getPlatformName() {
        return PLATFORM_OPENAI;
    }
}
