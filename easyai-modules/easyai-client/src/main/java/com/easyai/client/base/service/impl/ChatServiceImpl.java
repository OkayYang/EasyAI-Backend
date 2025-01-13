package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.ChatMapper;
import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.service.IChatService;

/**
 * 用户会话Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
@Service
public class ChatServiceImpl implements IChatService
{
    @Autowired
    private ChatMapper chatMapper;

    /**
     * 查询用户会话
     *
     * @param sessionId 用户会话主键
     * @return 用户会话
     */
    @Override
    public Chat selectChatBySessionId(String sessionId)
    {
        return chatMapper.selectChatBySessionId(sessionId);
    }

    /**
     * 查询用户会话列表
     *
     * @param chat 用户会话
     * @return 用户会话
     */
    @Override
    public List<Chat> selectChatList(Chat chat)
    {
        return chatMapper.selectChatList(chat);
    }

    /**
     * 新增用户会话
     *
     * @param chat 用户会话
     * @return 结果
     */
    @Override
    public int insertChat(Chat chat)
    {
        chat.setCreateTime(DateUtils.getNowDate());
        return chatMapper.insertChat(chat);
    }

    /**
     * 修改用户会话
     *
     * @param chat 用户会话
     * @return 结果
     */
    @Override
    public int updateChat(Chat chat)
    {
        chat.setUpdateTime(DateUtils.getNowDate());
        return chatMapper.updateChat(chat);
    }

    /**
     * 批量删除用户会话
     *
     * @param sessionIds 需要删除的用户会话主键
     * @return 结果
     */
    @Override
    public int deleteChatBySessionIds(String[] sessionIds)
    {
        return chatMapper.deleteChatBySessionIds(sessionIds);
    }

    /**
     * 删除用户会话信息
     *
     * @param sessionId 用户会话主键
     * @return 结果
     */
    @Override
    public int deleteChatBySessionId(String sessionId)
    {
        return chatMapper.deleteChatBySessionId(sessionId);
    }
}
