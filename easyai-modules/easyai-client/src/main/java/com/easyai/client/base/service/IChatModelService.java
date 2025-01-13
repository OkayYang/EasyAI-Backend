package com.easyai.client.base.service;

import java.util.List;
import com.easyai.client.base.domain.ChatModel;

/**
 * AI 模型Service接口
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
public interface IChatModelService
{
    /**
     * 查询AI 模型
     *
     * @param id AI 模型主键
     * @return AI 模型
     */
    public ChatModel selectChatModelById(Long id);

    /**
     * 查询AI 模型列表
     *
     * @param chatModel AI 模型
     * @return AI 模型集合
     */
    public List<ChatModel> selectChatModelList(ChatModel chatModel);

    /**
     * 新增AI 模型
     *
     * @param chatModel AI 模型
     * @return 结果
     */
    public int insertChatModel(ChatModel chatModel);

    /**
     * 修改AI 模型
     *
     * @param chatModel AI 模型
     * @return 结果
     */
    public int updateChatModel(ChatModel chatModel);

    /**
     * 批量删除AI 模型
     *
     * @param ids 需要删除的AI 模型主键集合
     * @return 结果
     */
    public int deleteChatModelByIds(Long[] ids);

    /**
     * 删除AI 模型信息
     *
     * @param id AI 模型主键
     * @return 结果
     */
    public int deleteChatModelById(Long id);
}
