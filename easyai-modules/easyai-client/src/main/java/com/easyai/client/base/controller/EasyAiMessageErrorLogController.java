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
import com.easyai.client.base.domain.EasyAiMessageErrorLog;
import com.easyai.client.base.service.IEasyAiMessageErrorLogService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 消息异常日志Controller
 *
 * @author xiaoshuaige
 * @date 2024-12-29
 */
@RestController
@RequestMapping("/message_error_log")
public class EasyAiMessageErrorLogController extends BaseController
{
    @Autowired
    private IEasyAiMessageErrorLogService easyAiMessageErrorLogService;

    /**
     * 查询消息异常日志列表
     */
    @RequiresPermissions("client:message_error_log:list")
    @GetMapping("/list")
    public TableDataInfo list(EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        startPage();
        List<EasyAiMessageErrorLog> list = easyAiMessageErrorLogService.selectEasyAiMessageErrorLogList(easyAiMessageErrorLog);
        return getDataTable(list);
    }

    /**
     * 导出消息异常日志列表
     */
    @RequiresPermissions("client:message_error_log:export")
    @Log(title = "消息异常日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        List<EasyAiMessageErrorLog> list = easyAiMessageErrorLogService.selectEasyAiMessageErrorLogList(easyAiMessageErrorLog);
        ExcelUtil<EasyAiMessageErrorLog> util = new ExcelUtil<EasyAiMessageErrorLog>(EasyAiMessageErrorLog.class);
        util.exportExcel(response, list, "消息异常日志数据");
    }

    /**
     * 获取消息异常日志详细信息
     */
    @RequiresPermissions("client:message_error_log:query")
    @GetMapping(value = "/{messageErrorLogId}")
    public AjaxResult getInfo(@PathVariable("messageErrorLogId") int messageErrorLogId)
    {
        return success(easyAiMessageErrorLogService.selectEasyAiMessageErrorLogByMessageErrorLogId(messageErrorLogId));
    }

    /**
     * 新增消息异常日志
     */
    @RequiresPermissions("client:message_error_log:add")
    @Log(title = "消息异常日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        return toAjax(easyAiMessageErrorLogService.insertEasyAiMessageErrorLog(easyAiMessageErrorLog));
    }

    /**
     * 修改消息异常日志
     */
    @RequiresPermissions("client:message_error_log:edit")
    @Log(title = "消息异常日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        return toAjax(easyAiMessageErrorLogService.updateEasyAiMessageErrorLog(easyAiMessageErrorLog));
    }

    /**
     * 删除消息异常日志
     */
    @RequiresPermissions("client:message_error_log:remove")
    @Log(title = "消息异常日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageErrorLogIds}")
    public AjaxResult remove(@PathVariable int[] messageErrorLogIds)
    {
        return toAjax(easyAiMessageErrorLogService.deleteEasyAiMessageErrorLogByMessageErrorLogIds(messageErrorLogIds));
    }
}
