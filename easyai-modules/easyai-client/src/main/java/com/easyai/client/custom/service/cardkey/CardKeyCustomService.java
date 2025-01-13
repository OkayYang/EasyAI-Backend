package com.easyai.client.custom.service.cardkey;

import com.easyai.client.custom.controller.card.vo.CardKeyUsedListRespBody;
import com.easyai.client.custom.controller.card.vo.CardRewardRespBody;
import com.easyai.client.custom.controller.card.vo.UseCardKeyBody;
import com.easyai.client.custom.enums.CardKeyStatusEnum;
import com.easyai.client.custom.mapper.CardKeyCustomMapper;
import com.easyai.client.custom.mapper.UserCardLogCustomMapper;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.base.domain.CardKey;
import com.easyai.client.base.domain.UserCardLog;
import com.easyai.common.core.constant.CacheConstants;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.security.utils.SecurityUtils;
import com.easyai.system.api.model.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author XuShiPing
 * @since 2024/10/21  15:24
 */

@Service
public class CardKeyCustomService implements ICardKeyCustomService {

    @Autowired
    private CardKeyCustomMapper cardKeyCustomMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Autowired
    private UserCardLogCustomMapper userCardLogCustomMapper;


    public CardRewardRespBody useCardKey(UseCardKeyBody useCardKeyBody) {

        String key = useCardKeyBody.getCardKey();
        CardKey cardKey = cardKeyCustomMapper.selectCardKeyByKey(key);
        if (cardKey == null) {
            throw new ServiceException("卡密错误!");
        }
        if (CardKeyStatusEnum.Card_USED.getValue().equals(cardKey.getStatus())) {
            throw new ServiceException("卡密已被使用过!");
        }

        // 添加 算力
        Long reward = cardKey.getReward();
        Long userId = SecurityUtils.getUserId();
        userCustomMapper.addUserPowerById(userId, reward);
        // 标记卡密为使用过
        cardKeyCustomMapper.updateCardKeyStatus(key, CardKeyStatusEnum.Card_USED.getValue());


        // 日志 记录用户使用卡密情况
        UserCardLog userCardLog = new UserCardLog();
        userCardLog.setEmail(SecurityUtils.getUsername());
        userCardLog.setCategory(cardKey.getCategory());
        userCardLog.setCardKey(key);
        userCardLog.setReward(reward);
        userCardLogCustomMapper.insertUserCardLog(userCardLog);


        // return
        CardRewardRespBody cardRewardRespBody = new CardRewardRespBody();
        BeanUtils.copyProperties(cardKey, cardRewardRespBody);
        return cardRewardRespBody;
    }

    @Override
    public List<CardKeyUsedListRespBody> getCardUsedList() {
        String email = SecurityUtils.getUsername();
        List<UserCardLog> userCardLogs = userCardLogCustomMapper.selectUserCardUsedList(email);
        List<CardKeyUsedListRespBody> cardKeyUsedListRespBodyList = new ArrayList<>();

        for (UserCardLog userCardLog : userCardLogs) {
            CardKeyUsedListRespBody respBody = new CardKeyUsedListRespBody();
            BeanUtils.copyProperties(userCardLog, respBody);
            cardKeyUsedListRespBodyList.add(respBody);
        }

        return cardKeyUsedListRespBodyList;
    }
}
