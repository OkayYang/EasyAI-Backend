package com.easyai.client.base.service;

import java.util.List;
import com.easyai.client.base.domain.ApiKey;

/**
 * 密钥管理Service接口
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public interface IApiKeyService
{
    /**
     * 查询密钥管理
     *
     * @param id 密钥管理主键
     * @return 密钥管理
     */
    public ApiKey selectApiKeyById(Long id);

    /**
     * 查询密钥管理列表
     *
     * @param apiKey 密钥管理
     * @return 密钥管理集合
     */
    public List<ApiKey> selectApiKeyList(ApiKey apiKey);

    /**
     * 新增密钥管理
     *
     * @param apiKey 密钥管理
     * @return 结果
     */
    public int insertApiKey(ApiKey apiKey);

    /**
     * 修改密钥管理
     *
     * @param apiKey 密钥管理
     * @return 结果
     */
    public int updateApiKey(ApiKey apiKey);

    /**
     * 批量删除密钥管理
     *
     * @param ids 需要删除的密钥管理主键集合
     * @return 结果
     */
    public int deleteApiKeyByIds(Long[] ids);

    /**
     * 删除密钥管理信息
     *
     * @param id 密钥管理主键
     * @return 结果
     */
    public int deleteApiKeyById(Long id);
}
