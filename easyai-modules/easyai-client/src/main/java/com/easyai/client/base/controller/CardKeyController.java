package com.easyai.client.base.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.easyai.client.base.vo.GenerateCardReqBody;
import com.easyai.common.core.utils.DateUtils;
import com.easyai.common.core.utils.uuid.IdUtils;
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
import com.easyai.client.base.domain.CardKey;
import com.easyai.client.base.service.ICardKeyService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.core.utils.poi.ExcelUtil;
import com.easyai.common.core.web.page.TableDataInfo;

/**
 * 卡密Controller
 *
 * @author xiaoshuaige
 * @date 2024-10-18
 */
@RestController
@RequestMapping("/card_key")
public class CardKeyController extends BaseController {
    @Autowired
    private ICardKeyService cardKeyService;

    /**
     * 查询卡密列表
     */
    @RequiresPermissions("client:card_key:list")
    @GetMapping("/list")
    public TableDataInfo list(CardKey cardKey) {
        startPage();
        List<CardKey> list = cardKeyService.selectCardKeyList(cardKey);
        return getDataTable(list);
    }

    /**
     * 导出卡密列表
     */
    @RequiresPermissions("client:card_key:export")
    @Log(title = "卡密", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CardKey cardKey) {
        List<CardKey> list = cardKeyService.selectCardKeyList(cardKey);
        ExcelUtil<CardKey> util = new ExcelUtil<CardKey>(CardKey.class);
        util.exportExcel(response, list, "卡密数据");
    }

    /**
     * 获取卡密详细信息
     */
    @RequiresPermissions("client:card_key:query")
    @GetMapping(value = "/{cardKeyId}")
    public AjaxResult getInfo(@PathVariable("cardKeyId") Long cardKeyId) {
        return success(cardKeyService.selectCardKeyByCardKeyId(cardKeyId));
    }

    /**
     * 新增卡密
     */
    @RequiresPermissions("client:card_key:add")
    @Log(title = "卡密", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CardKey cardKey) {
        return toAjax(cardKeyService.insertCardKey(cardKey));
    }

    /**
     * 修改卡密
     */
    @RequiresPermissions("client:card_key:edit")
    @Log(title = "卡密", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CardKey cardKey) {
        return toAjax(cardKeyService.updateCardKey(cardKey));
    }

    /**
     * 删除卡密
     */
    @RequiresPermissions("client:card_key:remove")
    @Log(title = "卡密", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cardKeyIds}")
    public AjaxResult remove(@PathVariable Long[] cardKeyIds) {
        return toAjax(cardKeyService.deleteCardKeyByCardKeyIds(cardKeyIds));
    }

    @RequiresPermissions("client:card_key:add")
    @Log(title = "卡密", businessType = BusinessType.INSERT)
    @PostMapping("/batchAddCard")
    public AjaxResult batchAddCard(@Valid @RequestBody GenerateCardReqBody generateCardReqBody) {
        return toAjax(cardKeyService.batchInsertCardKey(generateCardReqBody));
    }
}
