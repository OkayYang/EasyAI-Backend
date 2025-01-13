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
import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.service.IApiKeyService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 密钥管理Controller
 *
 * @author xiaoshuaige
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/apikey")
public class ApiKeyController extends BaseController
{
    @Autowired
    private IApiKeyService apiKeyService;

    /**
     * 查询密钥管理列表
     */
    @RequiresPermissions("client:apikey:list")
    @GetMapping("/list")
    public TableDataInfo list(ApiKey apiKey)
    {
        startPage();
        List<ApiKey> list = apiKeyService.selectApiKeyList(apiKey);
        return getDataTable(list);
    }

    /**
     * 导出密钥管理列表
     */
    @RequiresPermissions("client:apikey:export")
    @Log(title = "密钥管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApiKey apiKey)
    {
        List<ApiKey> list = apiKeyService.selectApiKeyList(apiKey);
        ExcelUtil<ApiKey> util = new ExcelUtil<ApiKey>(ApiKey.class);
        util.exportExcel(response, list, "密钥管理数据");
    }

    /**
     * 获取密钥管理详细信息
     */
    @RequiresPermissions("client:apikey:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(apiKeyService.selectApiKeyById(id));
    }

    /**
     * 新增密钥管理
     */
    @RequiresPermissions("client:apikey:add")
    @Log(title = "密钥管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApiKey apiKey)
    {
        return toAjax(apiKeyService.insertApiKey(apiKey));
    }

    /**
     * 修改密钥管理
     */
    @RequiresPermissions("client:apikey:edit")
    @Log(title = "密钥管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApiKey apiKey)
    {
        return toAjax(apiKeyService.updateApiKey(apiKey));
    }

    /**
     * 删除密钥管理
     */
    @RequiresPermissions("client:apikey:remove")
    @Log(title = "密钥管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(apiKeyService.deleteApiKeyByIds(ids));
    }
}
