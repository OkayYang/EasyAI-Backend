package com.easyai.client.base.mapper;

import java.util.List;
import com.easyai.client.base.domain.EasyAiMessage;

/**
 * 消息Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
public interface EasyAiMessageMapper
{
    /**
     * 查询消息
     *
     * @param messageId 消息主键
     * @return 消息
     */
    public EasyAiMessage selectEasyAiMessageByMessageId(String messageId);

    /**
     * 查询消息列表
     *
     * @param easyAiMessage 消息
     * @return 消息集合
     */
    public List<EasyAiMessage> selectEasyAiMessageList(EasyAiMessage easyAiMessage);

    /**
     * 新增消息
     *
     * @param easyAiMessage 消息
     * @return 结果
     */
    public int insertEasyAiMessage(EasyAiMessage easyAiMessage);

    /**
     * 修改消息
     *
     * @param easyAiMessage 消息
     * @return 结果
     */
    public int updateEasyAiMessage(EasyAiMessage easyAiMessage);

    /**
     * 删除消息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteEasyAiMessageByMessageId(String messageId);

    /**
     * 批量删除消息
     *
     * @param messageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEasyAiMessageByMessageIds(String[] messageIds);
}
