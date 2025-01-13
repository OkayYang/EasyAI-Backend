package com.easyai.client.custom.service.apikey;

import com.easyai.client.base.domain.ApiKey;

import java.util.Collection;

/**
 * @author XuShiPing
 * @since 2024/12/26  16:23
 */
public interface IApiKeyCustomService {

    ApiKey getRandomApiKey(String platform);
}
