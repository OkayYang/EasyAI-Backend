package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.mapper.EasyAiMessageMapper;

import java.util.List;

/**
 * 消息Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-23
 */
public interface EasyAiMessageCustomMapper extends EasyAiMessageMapper {
    EasyAiMessage selectEasyAiMessageByParentId(String session_id, String parent_id, String email);

    int insertEasyAiUserMessageWhenChat(String message_id, String session_id, String email, String content, String role, String parent_id, String model_name);

    int insertEasyAiAIMessageWhenChat(String message_id, String session_id, String email, String role, String parent_id, String model_name);

    void updateEasyAiMessageStatus(String message_id, String content_status);

    void updateEasyAiVisualMessageStatus(String message_id, String visual_content_status);

    void updateEasyAiMessageWhenComplete(String message_id, String content_status, int output_token);

    void updateEasyAiMessageVisualWhenComplete(String message_id, String content, String visual_content_status);

    void updateEasyAiParentMessageInputToken(String parent_id, int input_token);

    List<EasyAiMessage> getMessageListBySessionId(String session_id);


}
