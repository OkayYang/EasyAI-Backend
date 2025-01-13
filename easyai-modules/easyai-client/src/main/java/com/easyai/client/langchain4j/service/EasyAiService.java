package com.easyai.client.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

/**
 * @author XuShiPing
 * @since 2024/12/24  20:37
 */

public interface EasyAiService {
    TokenStream tokenStream(@MemoryId String memoryId, @UserMessage String userMessage);
}
