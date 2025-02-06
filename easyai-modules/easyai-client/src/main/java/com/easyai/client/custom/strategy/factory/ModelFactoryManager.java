package com.easyai.client.custom.strategy.factory;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.base.mapper.ApiKeyMapper;
import com.easyai.client.custom.mapper.ApiKeyCustomMapper;
import com.easyai.client.custom.strategy.ModelFactory;
import com.easyai.client.custom.strategy.VerificationCodeStrategy;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ModelFactoryManager {
    private final Map<String, ModelFactory> modelFactoryMap = new HashMap<>();

    // 使用构造器注入所有策略实现
    @Autowired
    public ModelFactoryManager(List<ModelFactory> modelFactoryList) {
        modelFactoryList.forEach(modelFactory -> {
            modelFactoryMap.putIfAbsent(modelFactory.getPlatformName(),modelFactory);
        });
    }
    public StreamingChatLanguageModel createModel(ApiKey apiKey, ChatModel chatModel) {

        ModelFactory modelFactory = modelFactoryMap.get(chatModel.getPlatform());
        if (modelFactory == null) {
            throw new RuntimeException("没有对应模型平台");
        }
        return modelFactory.createModel(apiKey, chatModel);

    }
}
