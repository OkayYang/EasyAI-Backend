package com.easyai.client.custom.strategy.impl;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.custom.strategy.ModelFactory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.stereotype.Service;

import static com.easyai.client.custom.constant.EasyAIConstants.PLATFORM_OPENAI;

@Service
public class OpenAiModelFactory implements ModelFactory {
    @Override
    public StreamingChatLanguageModel createModel(ApiKey apiKey, ChatModel modelConfig) {
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
