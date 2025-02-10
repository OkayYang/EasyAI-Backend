package com.easyai.client.springai.factory;

import com.easyai.client.base.domain.ApiKey;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpringAiChatModelFactoryManager {
    private final Map<String, SpringAiChatModelFactory> modelFactoryMap = new HashMap<>();

    // 使用构造器注入所有策略实现
    @Autowired
    public SpringAiChatModelFactoryManager(List<SpringAiChatModelFactory> modelFactoryList) {
        modelFactoryList.forEach(modelFactory -> {
            modelFactoryMap.putIfAbsent(modelFactory.getPlatformName(),modelFactory);
        });
    }
    public ChatModel createChatModel(ApiKey apiKey, com.easyai.client.base.domain.ChatModel chatModel) {

        SpringAiChatModelFactory modelFactory = modelFactoryMap.get(chatModel.getPlatform());
        if (modelFactory == null) {
            throw new RuntimeException("没有对应模型平台");
        }
        return modelFactory.createChatModel(apiKey, chatModel);

    }
}
