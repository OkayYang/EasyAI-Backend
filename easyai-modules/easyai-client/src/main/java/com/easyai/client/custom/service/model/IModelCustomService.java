package com.easyai.client.custom.service.model;

import com.easyai.client.custom.controller.unauth.vo.ModelRespBody;

import java.util.List;
import java.util.Map;

public interface IModelCustomService {
    Map<String,List<ModelRespBody>> getAllModels();
}
