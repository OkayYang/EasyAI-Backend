package com.easyai.client.springai.factory;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiChatModel;
import org.springframework.ai.chat.model.ChatModel;

public interface SpringAiChatModelFactory {
    ChatModel createChatModel(ApiKey apiKey, EasyAiChatModel modelConfig);
    String getPlatformName();
}
