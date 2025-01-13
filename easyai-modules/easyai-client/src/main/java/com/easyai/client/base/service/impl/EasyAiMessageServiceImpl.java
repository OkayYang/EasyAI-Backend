package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.EasyAiMessageMapper;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.service.IEasyAiMessageService;

/**
 * 消息Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
@Service
public class EasyAiMessageServiceImpl implements IEasyAiMessageService
{
    @Autowired
    private EasyAiMessageMapper easyAiMessageMapper;

    /**
     * 查询消息
     *
     * @param messageId 消息主键
     * @return 消息
     */
    @Override
    public EasyAiMessage selectEasyAiMessageByMessageId(String messageId)
    {
        return easyAiMessageMapper.selectEasyAiMessageByMessageId(messageId);
    }

    /**
     * 查询消息列表
     *
     * @param easyAiMessage 消息
     * @return 消息
     */
    @Override
    public List<EasyAiMessage> selectEasyAiMessageList(EasyAiMessage easyAiMessage)
    {
        return easyAiMessageMapper.selectEasyAiMessageList(easyAiMessage);
    }

    /**
     * 新增消息
     *
     * @param easyAiMessage 消息
     * @return 结果
     */
    @Override
    public int insertEasyAiMessage(EasyAiMessage easyAiMessage)
    {
        easyAiMessage.setCreateTime(DateUtils.getNowDate());
        return easyAiMessageMapper.insertEasyAiMessage(easyAiMessage);
    }

    /**
     * 修改消息
     *
     * @param easyAiMessage 消息
     * @return 结果
     */
    @Override
    public int updateEasyAiMessage(EasyAiMessage easyAiMessage)
    {
        easyAiMessage.setUpdateTime(DateUtils.getNowDate());
        return easyAiMessageMapper.updateEasyAiMessage(easyAiMessage);
    }

    /**
     * 批量删除消息
     *
     * @param messageIds 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageByMessageIds(String[] messageIds)
    {
        return easyAiMessageMapper.deleteEasyAiMessageByMessageIds(messageIds);
    }

    /**
     * 删除消息信息
     *
     * @param messageId 消息主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageByMessageId(String messageId)
    {
        return easyAiMessageMapper.deleteEasyAiMessageByMessageId(messageId);
    }
}
