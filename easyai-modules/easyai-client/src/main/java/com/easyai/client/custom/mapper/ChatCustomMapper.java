package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.mapper.ChatMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/17  19:01
 */
public interface ChatCustomMapper extends ChatMapper {

    void deleteChat(String session_id,int status);

    List<Chat> getChatListByEmailOrderByTime(@Param("email") String email,int status);

    Chat getChatBySessionId(String email,String session_id,int status);

    // 以下都是关于记忆的操作
    String getChatMemory(String session_id);

    void updateChatMemory(String session_id,String memory);

    void deleteChatMemory(String session_id);

}
