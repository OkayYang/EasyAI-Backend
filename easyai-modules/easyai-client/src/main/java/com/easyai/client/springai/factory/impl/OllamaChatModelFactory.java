package com.easyai.client.springai.factory.impl;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiChatModel;
import com.easyai.client.springai.factory.SpringAiChatModelFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static com.easyai.client.custom.constant.EasyAIConstants.AUTHORIZATION;
import static com.easyai.client.custom.constant.EasyAIConstants.PLATFORM_OLLAMA;

@Service
public class OllamaChatModelFactory implements SpringAiChatModelFactory {
    @Override
    public ChatModel createChatModel(ApiKey apiKey, EasyAiChatModel modelConfig) {
        RestClient.Builder restClientBuilder = RestClient.builder()
                .defaultHeaders(headers -> headers.set(AUTHORIZATION, apiKey.getApiKey()));

        WebClient.Builder webClientBuilder = WebClient.builder()
                .defaultHeaders(headers -> headers.set(AUTHORIZATION, apiKey.getApiKey()));
        OllamaApi ollamaApi = new OllamaApi(apiKey.getProxyUrl(),restClientBuilder,webClientBuilder);
        OllamaOptions ollamaOptions = OllamaOptions.builder()
                .model(modelConfig.getModelName())
                .temperature(modelConfig.getTemperature())
                .build();
        return OllamaChatModel.builder().ollamaApi(ollamaApi).defaultOptions(ollamaOptions).build();

    }


    @Override
    public String getPlatformName() {

        return PLATFORM_OLLAMA;

    }
}
