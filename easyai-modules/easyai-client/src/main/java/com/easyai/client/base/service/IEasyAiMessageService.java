package com.easyai.client.base.service;

import java.util.List;
import com.easyai.client.base.domain.EasyAiMessage;

/**
 * 消息Service接口
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
public interface IEasyAiMessageService
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
     * 批量删除消息
     *
     * @param messageIds 需要删除的消息主键集合
     * @return 结果
     */
    public int deleteEasyAiMessageByMessageIds(String[] messageIds);

    /**
     * 删除消息信息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteEasyAiMessageByMessageId(String messageId);
}
