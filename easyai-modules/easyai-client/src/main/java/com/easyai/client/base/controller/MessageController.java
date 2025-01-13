package com.easyai.client.base.controller;

import java.util.List;

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
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.service.IEasyAiMessageService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 消息Controller
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController
{
    @Autowired
    private IEasyAiMessageService easyAiMessageService;

    /**
     * 查询消息列表
     */
    @RequiresPermissions("client:message:list")
    @GetMapping("/list")
    public TableDataInfo list(EasyAiMessage easyAiMessage)
    {
        startPage();
        List<EasyAiMessage> list = easyAiMessageService.selectEasyAiMessageList(easyAiMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息列表
     */
    @RequiresPermissions("client:message:export")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EasyAiMessage easyAiMessage)
    {
        List<EasyAiMessage> list = easyAiMessageService.selectEasyAiMessageList(easyAiMessage);
        ExcelUtil<EasyAiMessage> util = new ExcelUtil<EasyAiMessage>(EasyAiMessage.class);
        util.exportExcel(response, list, "消息数据");
    }

    /**
     * 获取消息详细信息
     */
    @RequiresPermissions("client:message:query")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") String messageId)
    {
        return success(easyAiMessageService.selectEasyAiMessageByMessageId(messageId));
    }

    /**
     * 新增消息
     */
    @RequiresPermissions("client:message:add")
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EasyAiMessage easyAiMessage)
    {
        return toAjax(easyAiMessageService.insertEasyAiMessage(easyAiMessage));
    }

    /**
     * 修改消息
     */
    @RequiresPermissions("client:message:edit")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EasyAiMessage easyAiMessage)
    {
        return toAjax(easyAiMessageService.updateEasyAiMessage(easyAiMessage));
    }

    /**
     * 删除消息
     */
    @RequiresPermissions("client:message:remove")
    @Log(title = "消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable String[] messageIds)
    {
        return toAjax(easyAiMessageService.deleteEasyAiMessageByMessageIds(messageIds));
    }
}
