package com.easyai.client.langchain4j.factory;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiChatModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;

public interface ModelFactory {
    StreamingChatLanguageModel createModel(ApiKey apiKey, EasyAiChatModel modelConfig);
    String getPlatformName();
}
