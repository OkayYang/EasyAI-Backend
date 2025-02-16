package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.EasyAiChatModel;
import com.easyai.client.base.mapper.ChatModelMapper;

import java.util.List;

/**
 * AI 模型Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public interface ChatModelCustomMapper extends ChatModelMapper
{

    EasyAiChatModel getModelByName(String modelName);
    List<EasyAiChatModel> getAllModels();
}
