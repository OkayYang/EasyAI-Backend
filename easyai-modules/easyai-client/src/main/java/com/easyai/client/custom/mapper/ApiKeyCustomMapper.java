package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.mapper.ApiKeyMapper;

import java.util.List;

/**
 * 密钥管理Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public interface ApiKeyCustomMapper extends ApiKeyMapper {

    List<ApiKey> getApiKeyListByPlatform(String platform, int status, int finish);

    void updateApiKeyFinishStatus(int id, int finish);

    void updateApiKeyStatus(int id, int status);
}
