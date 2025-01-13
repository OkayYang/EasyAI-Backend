package com.easyai.client.custom.service.apikey;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.custom.enums.ApiKeyFinishStatus;
import com.easyai.client.custom.enums.ApiKeyStatusEnum;
import com.easyai.client.custom.mapper.ApiKeyCustomMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/26  16:23
 */

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
public class ApiKeyCustomService implements IApiKeyCustomService {

    @Autowired
    private ApiKeyCustomMapper apiKeyCustomMapper;


    // 在ruoyi管理员中 也添加了缓存清除策略 删除请注意！
    @Cacheable(value = "apikeyList")
    public List<ApiKey> getApiKeyListByPlatform(String platform) {
        return apiKeyCustomMapper.getApiKeyListByPlatform(platform, ApiKeyStatusEnum.OPEN.getValue(), ApiKeyFinishStatus.UNFINISHED.getValue());
    }

    @Override
    public ApiKey getRandomApiKey(String platform) {
        // 通过 AopContext 获取代理对象来调用
        ApiKeyCustomService service = (ApiKeyCustomService) AopContext.currentProxy();
        List<ApiKey> apiKeyListByPlatform = service.getApiKeyListByPlatform(platform);
        if (apiKeyListByPlatform.isEmpty()) {
            throw new RuntimeException("已无可用的Key 请联系管理员，有重大奖励!!!");
        }
        Collections.shuffle(apiKeyListByPlatform);
        return apiKeyListByPlatform.get(0);
    }

    @CacheEvict(value="apikeyList",allEntries = true)
    public void setApiKeyFinish(int id){
        apiKeyCustomMapper.updateApiKeyFinishStatus(id,ApiKeyFinishStatus.FINISH.getValue());
    }

    @CacheEvict(value="apikeyList",allEntries = true)
    public void setApiKeyOff(int id){
        apiKeyCustomMapper.updateApiKeyStatus(id,ApiKeyStatusEnum.OFF.getValue());
    }
}
