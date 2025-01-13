package com.easyai.client.custom.service.cardkey;

import com.easyai.client.custom.controller.card.vo.CardKeyUsedListRespBody;
import com.easyai.client.custom.controller.card.vo.CardRewardRespBody;
import com.easyai.client.custom.controller.card.vo.UseCardKeyBody;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  15:24
 */
public interface ICardKeyCustomService {

    /**
     * 包含 卡密使用 和卡密记录
     * @param useCardKeyBody
     */
    public CardRewardRespBody useCardKey(UseCardKeyBody useCardKeyBody);

    /**
     *  获得卡密使用列表
     */
    public List<CardKeyUsedListRespBody> getCardUsedList();
}
