package com.easyai.client.custom.controller.message;

import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.client.custom.service.message.IMessageCustomService;
import com.easyai.client.custom.service.message.MessageCustomService;
import com.easyai.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XuShiPing
 * @since 2024/12/28  20:57
 */

@RestController
@RequestMapping("/easyai/message")
public class EasyAIMessageController {

    @Autowired
    private IMessageCustomService messageCustomService;

    @GetMapping("/list")
    public AjaxResult getMessageList(String session_id) {
        return AjaxResult.success(messageCustomService.getMessageList(session_id));
    }
}
