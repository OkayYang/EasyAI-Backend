package com.easyai.client.custom.controller.card;

import com.easyai.client.custom.controller.card.vo.CardKeyUsedListRespBody;
import com.easyai.client.custom.controller.card.vo.CardRewardRespBody;
import com.easyai.client.custom.controller.card.vo.UseCardKeyBody;
import com.easyai.client.custom.service.cardkey.CardKeyCustomService;
import com.easyai.common.core.domain.R;
import com.easyai.common.core.web.domain.AjaxResult;
import com.easyai.common.security.utils.SecurityUtils;
import com.easyai.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  16:20
 */
@RestController
@RequestMapping("/easyai/card")
public class EasyAICardController {

    @Autowired
    private CardKeyCustomService cardKeyCustomService;

    @PostMapping("/inputCardKey")
    public AjaxResult InputCardKey(@RequestBody UseCardKeyBody useCardKeyBody) {
        CardRewardRespBody cardRewardRespBody= cardKeyCustomService.useCardKey(useCardKeyBody);
        return AjaxResult.success(cardRewardRespBody);
    }

    @GetMapping("/getUsedCardList")
    public AjaxResult getUsedCardList() {
        List<CardKeyUsedListRespBody> cardUsedList = cardKeyCustomService.getCardUsedList();
        return AjaxResult.success(cardUsedList);
    }

}
