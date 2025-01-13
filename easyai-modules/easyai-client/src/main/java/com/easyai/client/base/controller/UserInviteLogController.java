package com.easyai.client.base.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;

import com.easyai.client.base.domain.UserInviteLog;
import com.easyai.client.base.service.IUserInviteLogService;
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
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 用户邀请日志Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/invite_log")
public class UserInviteLogController extends BaseController
{
    @Autowired
    private IUserInviteLogService userInviteLogService;

    /**
     * 查询用户邀请日志列表
     */
    @RequiresPermissions("client:invite_log:list")
    @GetMapping("/list")
    public TableDataInfo list(UserInviteLog UserInviteLog)
    {
        startPage();
        List<UserInviteLog> list = userInviteLogService.selectUserInviteLogList(UserInviteLog);
        return getDataTable(list);
    }

    /**
     * 导出用户邀请日志列表
     */
    @RequiresPermissions("client:invite_log:export")
    @Log(title = "用户邀请日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserInviteLog UserInviteLog)
    {
        List<UserInviteLog> list = userInviteLogService.selectUserInviteLogList(UserInviteLog);
        ExcelUtil<UserInviteLog> util = new ExcelUtil<UserInviteLog>(UserInviteLog.class);
        util.exportExcel(response, list, "用户邀请日志数据");
    }

    /**
     * 获取用户邀请日志详细信息
     */
    @RequiresPermissions("client:invite_log:query")
    @GetMapping(value = "/{inviteId}")
    public AjaxResult getInfo(@PathVariable("inviteId") Long inviteId)
    {
        return success(userInviteLogService.selectUserInviteLogByInviteId(inviteId));
    }

    /**
     * 新增用户邀请日志
     */
    @RequiresPermissions("client:invite_log:add")
    @Log(title = "用户邀请日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserInviteLog UserInviteLog)
    {
        return toAjax(userInviteLogService.insertUserInviteLog(UserInviteLog));
    }

    /**
     * 修改用户邀请日志
     */
    @RequiresPermissions("client:invite_log:edit")
    @Log(title = "用户邀请日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserInviteLog UserInviteLog)
    {
        return toAjax(userInviteLogService.updateUserInviteLog(UserInviteLog));
    }

    /**
     * 删除用户邀请日志
     */
    @RequiresPermissions("client:invite_log:remove")
    @Log(title = "用户邀请日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inviteIds}")
    public AjaxResult remove(@PathVariable Long[] inviteIds)
    {
        return toAjax(userInviteLogService.deleteUserInviteLogByInviteIds(inviteIds));
    }
}
