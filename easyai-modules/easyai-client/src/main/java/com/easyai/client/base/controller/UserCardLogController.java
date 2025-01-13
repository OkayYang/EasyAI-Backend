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
import com.easyai.client.base.domain.UserCardLog;
import com.easyai.client.base.service.IUserCardLogService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 用户卡密日志Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
@RestController
@RequestMapping("/user_card_log")
public class UserCardLogController extends BaseController
{
    @Autowired
    private IUserCardLogService userCardLogService;

    /**
     * 查询用户卡密日志列表
     */
    @RequiresPermissions("client:user_card_log:list")
    @GetMapping("/list")
    public TableDataInfo list(UserCardLog userCardLog)
    {
        startPage();
        List<UserCardLog> list = userCardLogService.selectUserCardLogList(userCardLog);
        return getDataTable(list);
    }

    /**
     * 导出用户卡密日志列表
     */
    @RequiresPermissions("client:user_card_log:export")
    @Log(title = "用户卡密日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserCardLog userCardLog)
    {
        List<UserCardLog> list = userCardLogService.selectUserCardLogList(userCardLog);
        ExcelUtil<UserCardLog> util = new ExcelUtil<UserCardLog>(UserCardLog.class);
        util.exportExcel(response, list, "用户卡密日志数据");
    }

    /**
     * 获取用户卡密日志详细信息
     */
    @RequiresPermissions("client:user_card_log:query")
    @GetMapping(value = "/{userCardId}")
    public AjaxResult getInfo(@PathVariable("userCardId") Long userCardId)
    {
        return success(userCardLogService.selectUserCardLogByUserCardId(userCardId));
    }

    /**
     * 新增用户卡密日志
     */
    @RequiresPermissions("client:user_card_log:add")
    @Log(title = "用户卡密日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCardLog userCardLog)
    {
        return toAjax(userCardLogService.insertUserCardLog(userCardLog));
    }

    /**
     * 修改用户卡密日志
     */
    @RequiresPermissions("client:user_card_log:edit")
    @Log(title = "用户卡密日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCardLog userCardLog)
    {
        return toAjax(userCardLogService.updateUserCardLog(userCardLog));
    }

    /**
     * 删除用户卡密日志
     */
    @RequiresPermissions("client:user_card_log:remove")
    @Log(title = "用户卡密日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userCardIds}")
    public AjaxResult remove(@PathVariable Long[] userCardIds)
    {
        return toAjax(userCardLogService.deleteUserCardLogByUserCardIds(userCardIds));
    }
}
