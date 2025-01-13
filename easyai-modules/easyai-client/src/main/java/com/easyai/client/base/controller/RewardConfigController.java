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
import com.easyai.client.base.domain.RewardConfig;
import com.easyai.client.base.service.IRewardConfigService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 奖励配置Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-20
 */
@RestController
@RequestMapping("/reward_config")
public class RewardConfigController extends BaseController
{
    @Autowired
    private IRewardConfigService rewardConfigService;

    /**
     * 查询奖励配置列表
     */
    @RequiresPermissions("client:reward_config:list")
    @GetMapping("/list")
    public TableDataInfo list(RewardConfig rewardConfig)
    {
        startPage();
        List<RewardConfig> list = rewardConfigService.selectRewardConfigList(rewardConfig);
        return getDataTable(list);
    }

    /**
     * 导出奖励配置列表
     */
    @RequiresPermissions("client:reward_config:export")
    @Log(title = "奖励配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RewardConfig rewardConfig)
    {
        List<RewardConfig> list = rewardConfigService.selectRewardConfigList(rewardConfig);
        ExcelUtil<RewardConfig> util = new ExcelUtil<RewardConfig>(RewardConfig.class);
        util.exportExcel(response, list, "奖励配置数据");
    }

    /**
     * 获取奖励配置详细信息
     */
    @RequiresPermissions("client:reward_config:query")
    @GetMapping(value = "/{rewardConfigId}")
    public AjaxResult getInfo(@PathVariable("rewardConfigId") Long rewardConfigId)
    {
        return success(rewardConfigService.selectRewardConfigByRewardConfigId(rewardConfigId));
    }

    /**
     * 新增奖励配置
     */
    @RequiresPermissions("client:reward_config:add")
    @Log(title = "奖励配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RewardConfig rewardConfig)
    {
        return toAjax(rewardConfigService.insertRewardConfig(rewardConfig));
    }

    /**
     * 修改奖励配置
     */
    @RequiresPermissions("client:reward_config:edit")
    @Log(title = "奖励配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RewardConfig rewardConfig)
    {
        return toAjax(rewardConfigService.updateRewardConfig(rewardConfig));
    }

    /**
     * 删除奖励配置
     */
    @RequiresPermissions("client:reward_config:remove")
    @Log(title = "奖励配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rewardConfigIds}")
    public AjaxResult remove(@PathVariable Long[] rewardConfigIds)
    {
        return toAjax(rewardConfigService.deleteRewardConfigByRewardConfigIds(rewardConfigIds));
    }
}
