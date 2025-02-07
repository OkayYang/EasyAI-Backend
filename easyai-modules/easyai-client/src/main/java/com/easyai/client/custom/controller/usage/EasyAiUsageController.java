package com.easyai.client.custom.controller.usage;

import com.easyai.client.custom.service.message.IMessageCustomService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "EasyAI用户端 - 使用明细")
@RestController
@RequestMapping("/easyai/usage")
public class EasyAiUsageController extends BaseController {
    @Autowired
    private IMessageCustomService messageCustomService;

    @GetMapping("/summary")
    public AjaxResult summary() {
        return AjaxResult.success(messageCustomService.getTokenUsageSummary());

    }

}
