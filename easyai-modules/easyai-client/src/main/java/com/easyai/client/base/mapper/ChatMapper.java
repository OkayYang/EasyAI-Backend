package com.easyai.client.base.mapper;

import java.util.List;
import com.easyai.client.base.domain.Chat;

/**
 * 用户会话Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
public interface ChatMapper
{
    /**
     * 查询用户会话
     *
     * @param sessionId 用户会话主键
     * @return 用户会话
     */
    public Chat selectChatBySessionId(String sessionId);

    /**
     * 查询用户会话列表
     *
     * @param chat 用户会话
     * @return 用户会话集合
     */
    public List<Chat> selectChatList(Chat chat);

    /**
     * 新增用户会话
     *
     * @param chat 用户会话
     * @return 结果
     */
    public int insertChat(Chat chat);

    /**
     * 修改用户会话
     *
     * @param chat 用户会话
     * @return 结果
     */
    public int updateChat(Chat chat);

    /**
     * 删除用户会话
     *
     * @param sessionId 用户会话主键
     * @return 结果
     */
    public int deleteChatBySessionId(String sessionId);

    /**
     * 批量删除用户会话
     *
     * @param sessionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChatBySessionIds(String[] sessionIds);
}
