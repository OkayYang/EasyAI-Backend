package com.easyai.client.base.controller;

import java.util.List;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.easyai.common.log.annotation.Log;
import com.easyai.common.log.enums.BusinessType;
import com.easyai.common.security.annotation.RequiresPermissions;
import com.easyai.client.base.domain.ChatModel;
import com.easyai.client.base.service.IChatModelService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * AI 模型Controller
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/chatModel")
public class ChatModelController extends BaseController
{
    @Autowired
    private IChatModelService chatModelService;

    /**
     * 查询AI 模型列表
     */
    @RequiresPermissions("client:chatModel:list")
    @GetMapping("/list")
    public TableDataInfo list(ChatModel chatModel)
    {
        startPage();
        List<ChatModel> list = chatModelService.selectChatModelList(chatModel);
        return getDataTable(list);
    }

    /**
     * 导出AI 模型列表
     */
    @RequiresPermissions("client:chatModel:export")
    @Log(title = "AI 模型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatModel chatModel)
    {
        List<ChatModel> list = chatModelService.selectChatModelList(chatModel);
        ExcelUtil<ChatModel> util = new ExcelUtil<ChatModel>(ChatModel.class);
        util.exportExcel(response, list, "AI 模型数据");
    }

    /**
     * 获取AI 模型详细信息
     */
    @RequiresPermissions("client:chatModel:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chatModelService.selectChatModelById(id));
    }

    /**
     * 新增AI 模型
     */
    @RequiresPermissions("client:chatModel:add")
    @Log(title = "AI 模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChatModel chatModel)
    {
        return toAjax(chatModelService.insertChatModel(chatModel));
    }

    /**
     * 修改AI 模型
     */
    @RequiresPermissions("client:chatModel:edit")
    @Log(title = "AI 模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChatModel chatModel)
    {
        return toAjax(chatModelService.updateChatModel(chatModel));
    }

    /**
     * 删除AI 模型
     */
    @RequiresPermissions("client:chatModel:remove")
    @Log(title = "AI 模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chatModelService.deleteChatModelByIds(ids));
    }
}
