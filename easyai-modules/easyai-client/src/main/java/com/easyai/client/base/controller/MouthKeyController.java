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
import com.easyai.client.base.domain.MouthKey;
import com.easyai.client.base.service.IMouthKeyService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 口令管理Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
@RestController
@RequestMapping("/mouth_key")
public class MouthKeyController extends BaseController
{
    @Autowired
    private IMouthKeyService mouthKeyService;

    /**
     * 查询口令管理列表
     */
    @RequiresPermissions("client:mouth_key:list")
    @GetMapping("/list")
    public TableDataInfo list(MouthKey mouthKey)
    {
        startPage();
        List<MouthKey> list = mouthKeyService.selectMouthKeyList(mouthKey);
        return getDataTable(list);
    }

    /**
     * 导出口令管理列表
     */
    @RequiresPermissions("client:mouth_key:export")
    @Log(title = "口令管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MouthKey mouthKey)
    {
        List<MouthKey> list = mouthKeyService.selectMouthKeyList(mouthKey);
        ExcelUtil<MouthKey> util = new ExcelUtil<MouthKey>(MouthKey.class);
        util.exportExcel(response, list, "口令管理数据");
    }

    /**
     * 获取口令管理详细信息
     */
    @RequiresPermissions("client:mouth_key:query")
    @GetMapping(value = "/{mouthKeyId}")
    public AjaxResult getInfo(@PathVariable("mouthKeyId") Long mouthKeyId)
    {
        return success(mouthKeyService.selectMouthKeyByMouthKeyId(mouthKeyId));
    }

    /**
     * 新增口令管理
     */
    @RequiresPermissions("client:mouth_key:add")
    @Log(title = "口令管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MouthKey mouthKey)
    {
        return toAjax(mouthKeyService.insertMouthKey(mouthKey));
    }

    /**
     * 修改口令管理
     */
    @RequiresPermissions("client:mouth_key:edit")
    @Log(title = "口令管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MouthKey mouthKey)
    {
        return toAjax(mouthKeyService.updateMouthKey(mouthKey));
    }

    /**
     * 删除口令管理
     */
    @RequiresPermissions("client:mouth_key:remove")
    @Log(title = "口令管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mouthKeyIds}")
    public AjaxResult remove(@PathVariable Long[] mouthKeyIds)
    {
        return toAjax(mouthKeyService.deleteMouthKeyByMouthKeyIds(mouthKeyIds));
    }
}
