package com.easyai.client.custom.mapper;

import com.easyai.client.base.mapper.EasyAiMessageErrorLogMapper;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:39
 */
public interface EasyAiMessageErrorLogCustomMapper extends EasyAiMessageErrorLogMapper {

   int insertMessageErrorLog(String session_id,String message_id,String email,String code,String type,String message,String api_key,String model_name);
}
