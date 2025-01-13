package com.easyai.client.langchain4j.store;

import com.easyai.client.custom.mapper.ChatCustomMapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;

/**
 * @author XuShiPing
 * @since 2024/12/23  23:51
 */

@Service
public class PersistentChatMemoryStore implements ChatMemoryStore {

    @Autowired
    private ChatCustomMapper chatCustomMapper;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String messages = chatCustomMapper.getChatMemory((String)memoryId);
        return messagesFromJson(messages);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        String json = messagesToJson(list);
        chatCustomMapper.updateChatMemory((String)memoryId, json);
    }

    @Override
    public void deleteMessages(Object memoryId) {

    }
}
