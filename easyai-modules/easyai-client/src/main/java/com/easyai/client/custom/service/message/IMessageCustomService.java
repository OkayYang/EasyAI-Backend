package com.easyai.client.custom.service.message;

import com.easyai.client.custom.controller.message.vo.MessageListBody;
import com.easyai.client.custom.controller.message.vo.MessageListRespBody;
import com.easyai.client.custom.controller.unauth.vo.ModelRespBody;
import com.easyai.client.custom.controller.usage.vo.UsageTokenDetailRespBody;

import java.util.List;
import java.util.Map;

/**
 * @author XuShiPing
 * @since 2024/12/28  21:00
 */
public interface IMessageCustomService {

    MessageListRespBody getMessageList(String session_id);

    List<UsageTokenDetailRespBody> getTokenUsageSummary();
}
