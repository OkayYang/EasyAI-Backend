package com.easyai.client.langchain4j.factory.impl;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiChatModel;
import com.easyai.client.langchain4j.factory.ModelFactory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.easyai.client.custom.constant.EasyAIConstants.AUTHORIZATION;
import static com.easyai.client.custom.constant.EasyAIConstants.PLATFORM_OLLAMA;

@Service
public class OllamaModelFactory implements ModelFactory {
    @Override
    public StreamingChatLanguageModel createModel(ApiKey apiKey, EasyAiChatModel modelConfig) {
        Map<String,String> head = new HashMap<>();
        head.put(AUTHORIZATION,apiKey.getApiKey());
        return OllamaStreamingChatModel.builder()
                .baseUrl(apiKey.getProxyUrl())
                .customHeaders(head)
                .modelName(modelConfig.getModelName())
                .build();
    }

    @Override
    public String getPlatformName() {
        return PLATFORM_OLLAMA;
    }
}
