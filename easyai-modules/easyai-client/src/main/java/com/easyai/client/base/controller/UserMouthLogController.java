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
import com.easyai.client.base.domain.UserMouthLog;
import com.easyai.client.base.service.IUserMouthLogService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 用户口令日志Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/user_mouth_log")
public class UserMouthLogController extends BaseController
{
    @Autowired
    private IUserMouthLogService userMouthLogService;

    /**
     * 查询用户口令日志列表
     */
    @RequiresPermissions("client:user_mouth_log:list")
    @GetMapping("/list")
    public TableDataInfo list(UserMouthLog userMouthLog)
    {
        startPage();
        List<UserMouthLog> list = userMouthLogService.selectUserMouthLogList(userMouthLog);
        return getDataTable(list);
    }

    /**
     * 导出用户口令日志列表
     */
    @RequiresPermissions("client:user_mouth_log:export")
    @Log(title = "用户口令日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserMouthLog userMouthLog)
    {
        List<UserMouthLog> list = userMouthLogService.selectUserMouthLogList(userMouthLog);
        ExcelUtil<UserMouthLog> util = new ExcelUtil<UserMouthLog>(UserMouthLog.class);
        util.exportExcel(response, list, "用户口令日志数据");
    }

    /**
     * 获取用户口令日志详细信息
     */
    @RequiresPermissions("client:user_mouth_log:query")
    @GetMapping(value = "/{mouthUserId}")
    public AjaxResult getInfo(@PathVariable("mouthUserId") Long mouthUserId)
    {
        return success(userMouthLogService.selectUserMouthLogByMouthUserId(mouthUserId));
    }

    /**
     * 新增用户口令日志
     */
    @RequiresPermissions("client:user_mouth_log:add")
    @Log(title = "用户口令日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserMouthLog userMouthLog)
    {
        return toAjax(userMouthLogService.insertUserMouthLog(userMouthLog));
    }

    /**
     * 修改用户口令日志
     */
    @RequiresPermissions("client:user_mouth_log:edit")
    @Log(title = "用户口令日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserMouthLog userMouthLog)
    {
        return toAjax(userMouthLogService.updateUserMouthLog(userMouthLog));
    }

    /**
     * 删除用户口令日志
     */
    @RequiresPermissions("client:user_mouth_log:remove")
    @Log(title = "用户口令日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mouthUserIds}")
    public AjaxResult remove(@PathVariable Long[] mouthUserIds)
    {
        return toAjax(userMouthLogService.deleteUserMouthLogByMouthUserIds(mouthUserIds));
    }
}
