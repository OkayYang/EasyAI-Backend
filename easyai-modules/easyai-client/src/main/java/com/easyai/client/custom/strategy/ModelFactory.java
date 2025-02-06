package com.easyai.client.custom.strategy;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.ChatModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;

public interface ModelFactory {
    StreamingChatLanguageModel createModel(ApiKey apiKey, ChatModel modelConfig);
    String getPlatformName();
}
