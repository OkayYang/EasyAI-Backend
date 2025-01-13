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
import com.easyai.client.base.domain.EasyAiMessageLog;
import com.easyai.client.base.service.IEasyAiMessageLogService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 消息日志Controller
 *
 * @author xiaoshuaiage
 * @date 2024-12-25
 */
@RestController
@RequestMapping("/message_log")
public class MessageLogController extends BaseController
{
    @Autowired
    private IEasyAiMessageLogService easyAiMessageLogService;

    /**
     * 查询消息日志列表
     */
    @RequiresPermissions("client:message_log:list")
    @GetMapping("/list")
    public TableDataInfo list(EasyAiMessageLog easyAiMessageLog)
    {
        startPage();
        List<EasyAiMessageLog> list = easyAiMessageLogService.selectEasyAiMessageLogList(easyAiMessageLog);
        return getDataTable(list);
    }

    /**
     * 导出消息日志列表
     */
    @RequiresPermissions("client:message_log:export")
    @Log(title = "消息日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EasyAiMessageLog easyAiMessageLog)
    {
        List<EasyAiMessageLog> list = easyAiMessageLogService.selectEasyAiMessageLogList(easyAiMessageLog);
        ExcelUtil<EasyAiMessageLog> util = new ExcelUtil<EasyAiMessageLog>(EasyAiMessageLog.class);
        util.exportExcel(response, list, "消息日志数据");
    }

    /**
     * 获取消息日志详细信息
     */
    @RequiresPermissions("client:message_log:query")
    @GetMapping(value = "/{messageLogId}")
    public AjaxResult getInfo(@PathVariable("messageLogId") String messageLogId)
    {
        return success(easyAiMessageLogService.selectEasyAiMessageLogByMessageLogId(messageLogId));
    }

    /**
     * 新增消息日志
     */
    @RequiresPermissions("client:message_log:add")
    @Log(title = "消息日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EasyAiMessageLog easyAiMessageLog)
    {
        return toAjax(easyAiMessageLogService.insertEasyAiMessageLog(easyAiMessageLog));
    }

    /**
     * 修改消息日志
     */
    @RequiresPermissions("client:message_log:edit")
    @Log(title = "消息日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EasyAiMessageLog easyAiMessageLog)
    {
        return toAjax(easyAiMessageLogService.updateEasyAiMessageLog(easyAiMessageLog));
    }

    /**
     * 删除消息日志
     */
    @RequiresPermissions("client:message_log:remove")
    @Log(title = "消息日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageLogIds}")
    public AjaxResult remove(@PathVariable String[] messageLogIds)
    {
        return toAjax(easyAiMessageLogService.deleteEasyAiMessageLogByMessageLogIds(messageLogIds));
    }
}
