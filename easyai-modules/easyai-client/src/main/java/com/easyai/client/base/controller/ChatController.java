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
import com.easyai.client.base.domain.Chat;
import com.easyai.client.base.service.IChatService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 用户会话Controller
 *
 * @author xiaoshuaige
 * @date 2024-12-28
 */
@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController
{
    @Autowired
    private IChatService chatService;

    /**
     * 查询用户会话列表
     */
    @RequiresPermissions("client:chat:list")
    @GetMapping("/list")
    public TableDataInfo list(Chat chat)
    {
        startPage();
        List<Chat> list = chatService.selectChatList(chat);
        return getDataTable(list);
    }

    /**
     * 导出用户会话列表
     */
    @RequiresPermissions("client:chat:export")
    @Log(title = "用户会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chat chat)
    {
        List<Chat> list = chatService.selectChatList(chat);
        ExcelUtil<Chat> util = new ExcelUtil<Chat>(Chat.class);
        util.exportExcel(response, list, "用户会话数据");
    }

    /**
     * 获取用户会话详细信息
     */
    @RequiresPermissions("client:chat:query")
    @GetMapping(value = "/{sessionId}")
    public AjaxResult getInfo(@PathVariable("sessionId") String sessionId)
    {
        return success(chatService.selectChatBySessionId(sessionId));
    }

    /**
     * 新增用户会话
     */
    @RequiresPermissions("client:chat:add")
    @Log(title = "用户会话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chat chat)
    {
        return toAjax(chatService.insertChat(chat));
    }

    /**
     * 修改用户会话
     */
    @RequiresPermissions("client:chat:edit")
    @Log(title = "用户会话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chat chat)
    {
        return toAjax(chatService.updateChat(chat));
    }

    /**
     * 删除用户会话
     */
    @RequiresPermissions("client:chat:remove")
    @Log(title = "用户会话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sessionIds}")
    public AjaxResult remove(@PathVariable String[] sessionIds)
    {
        return toAjax(chatService.deleteChatBySessionIds(sessionIds));
    }
}
