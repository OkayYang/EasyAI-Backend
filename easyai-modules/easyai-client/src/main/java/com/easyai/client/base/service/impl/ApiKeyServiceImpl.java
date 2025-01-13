package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.ApiKeyMapper;
import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.service.IApiKeyService;

/**
 * 密钥管理Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
@Service
public class ApiKeyServiceImpl implements IApiKeyService
{
    @Autowired
    private ApiKeyMapper apiKeyMapper;

    /**
     * 查询密钥管理
     *
     * @param id 密钥管理主键
     * @return 密钥管理
     */
    @Override
    public ApiKey selectApiKeyById(Long id)
    {
        return apiKeyMapper.selectApiKeyById(id);
    }

    /**
     * 查询密钥管理列表
     *
     * @param apiKey 密钥管理
     * @return 密钥管理
     */
    @Override
    public List<ApiKey> selectApiKeyList(ApiKey apiKey)
    {
        return apiKeyMapper.selectApiKeyList(apiKey);
    }

    /**
     * 新增密钥管理
     *
     * @param apiKey 密钥管理
     * @return 结果
     */
    @Override
    @CacheEvict(value = "apikeyList", allEntries = true)
    public int insertApiKey(ApiKey apiKey)
    {
        apiKey.setCreateTime(DateUtils.getNowDate());
        return apiKeyMapper.insertApiKey(apiKey);
    }

    /**
     * 修改密钥管理
     *
     * @param apiKey 密钥管理
     * @return 结果
     */
    @Override
    @CacheEvict(value = "apikeyList", allEntries = true)
    public int updateApiKey(ApiKey apiKey)
    {
        apiKey.setUpdateTime(DateUtils.getNowDate());
        return apiKeyMapper.updateApiKey(apiKey);
    }

    /**
     * 批量删除密钥管理
     *
     * @param ids 需要删除的密钥管理主键
     * @return 结果
     */
    @Override
    @CacheEvict(value = "apikeyList", allEntries = true)
    public int deleteApiKeyByIds(Long[] ids)
    {
        return apiKeyMapper.deleteApiKeyByIds(ids);
    }

    /**
     * 删除密钥管理信息
     *
     * @param id 密钥管理主键
     * @return 结果
     */
    @Override
    @CacheEvict(value = "apikeyList", allEntries = true)
    public int deleteApiKeyById(Long id)
    {
        return apiKeyMapper.deleteApiKeyById(id);
    }
}
