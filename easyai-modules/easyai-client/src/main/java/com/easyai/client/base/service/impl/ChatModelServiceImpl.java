package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.ChatModelMapper;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.base.service.IChatModelService;

/**
 * AI 模型Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
@Service
public class ChatModelServiceImpl implements IChatModelService
{
    @Autowired
    private ChatModelMapper chatModelMapper;

    /**
     * 查询AI 模型
     *
     * @param id AI 模型主键
     * @return AI 模型
     */
    @Override
    public ChatModel selectChatModelById(Long id)
    {
        return chatModelMapper.selectChatModelById(id);
    }

    /**
     * 查询AI 模型列表
     *
     * @param chatModel AI 模型
     * @return AI 模型
     */
    @Override
    public List<ChatModel> selectChatModelList(ChatModel chatModel)
    {
        return chatModelMapper.selectChatModelList(chatModel);
    }

    /**
     * 新增AI 模型
     *
     * @param chatModel AI 模型
     * @return 结果
     */
    @Override
    public int insertChatModel(ChatModel chatModel)
    {
        chatModel.setCreateTime(DateUtils.getNowDate());
        return chatModelMapper.insertChatModel(chatModel);
    }

    /**
     * 修改AI 模型
     *
     * @param chatModel AI 模型
     * @return 结果
     */
    @Override
    public int updateChatModel(ChatModel chatModel)
    {
        chatModel.setUpdateTime(DateUtils.getNowDate());
        return chatModelMapper.updateChatModel(chatModel);
    }

    /**
     * 批量删除AI 模型
     *
     * @param ids 需要删除的AI 模型主键
     * @return 结果
     */
    @Override
    public int deleteChatModelByIds(Long[] ids)
    {
        return chatModelMapper.deleteChatModelByIds(ids);
    }

    /**
     * 删除AI 模型信息
     *
     * @param id AI 模型主键
     * @return 结果
     */
    @Override
    public int deleteChatModelById(Long id)
    {
        return chatModelMapper.deleteChatModelById(id);
    }
}
