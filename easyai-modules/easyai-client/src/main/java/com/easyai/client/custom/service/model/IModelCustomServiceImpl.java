package com.easyai.client.custom.service.model;

import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.custom.controller.unauth.vo.ModelRespBody;
import com.easyai.client.custom.mapper.ChatModelCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IModelCustomServiceImpl implements IModelCustomService {
    @Autowired
    private ChatModelCustomMapper chatModelCustomMapper;


    @Override
    public Map<String, List<ModelRespBody>> getAllModels() {
        List<ChatModel> chatModels = chatModelCustomMapper.getAllModels();

        return chatModels.stream()
                .map(chatModel -> {
                    ModelRespBody respBody = new ModelRespBody();
                    respBody.setModelName(chatModel.getModelName());
                    respBody.setOrderNum(chatModel.getOrderNum());
                    respBody.setPrice(chatModel.getPrice());
                    respBody.setVersion(chatModel.getVersion());
                    return new AbstractMap.SimpleEntry<>(chatModel.getPlatform(), respBody);
                })
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

}
