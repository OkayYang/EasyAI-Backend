package com.easyai.client.springai.factory;

import com.easyai.client.base.domain.ApiKey;
import org.springframework.ai.chat.model.ChatModel;

public interface SpringAiChatModelFactory {
    ChatModel createChatModel(ApiKey apiKey, com.easyai.client.base.domain.ChatModel modelConfig);
    String getPlatformName();
}
