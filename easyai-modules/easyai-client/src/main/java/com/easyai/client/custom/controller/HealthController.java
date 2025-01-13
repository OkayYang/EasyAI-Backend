package com.easyai.client.custom.controller;


import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.mapper.ApiKeyMapper;
import com.easyai.client.base.service.IEasyAiMessageService;
import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.client.custom.service.apikey.IApiKeyCustomService;
import com.easyai.client.langchain4j.service.EasyAiService;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@RestController
public class HealthController {

    @Autowired
    public ApiKeyMapper apiKeyMapper;

    @Autowired
    public IApiKeyCustomService iApiKeyCustomService;

    @Autowired
    public EasyAiMessageCustomMapper easyAiMessageCustomMapper;

    @Autowired
    public RedisTemplate redisTemplate;

    @GetMapping("/index")
    public String Health() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public List<ApiKey> test() {
        ApiKey apiKey = new ApiKey();
        apiKey.setPlatform("OpenAI");
        System.out.println("?");
        List<ApiKey> apiKeys = apiKeyMapper.selectApiKeyList(apiKey);
        return apiKeys;
    }

    @GetMapping("/test1")
    public ApiKey test1() {
        ChatMemory chatMemory;
        ApiKey openAi = iApiKeyCustomService.getRandomApiKey("OpenAi");
        return openAi;
    }

    @GetMapping("/test2")
    public List<EasyAiMessage> test2() {
        return easyAiMessageCustomMapper.getMessageListBySessionId("c6f5f986-c7c2-4bf4-a4fe-cf4f9f274a89");
    }


}
