package com.easyai.client.base.service;

import java.util.List;

import com.easyai.client.base.domain.CardKey;
import com.easyai.client.base.vo.GenerateCardReqBody;

/**
 * 卡密Service接口
 *
 * @author xiaoshuaige
 * @date 2024-10-18
 */
public interface ICardKeyService
{
    /**
     * 查询卡密
     *
     * @param cardKeyId 卡密主键
     * @return 卡密
     */
    public CardKey selectCardKeyByCardKeyId(Long cardKeyId);

    /**
     * 查询卡密列表
     *
     * @param cardKey 卡密
     * @return 卡密集合
     */
    public List<CardKey> selectCardKeyList(CardKey cardKey);

    /**
     * 新增卡密
     *
     * @param cardKey 卡密
     * @return 结果
     */
    public int insertCardKey(CardKey cardKey);

    /**
     * 修改卡密
     *
     * @param cardKey 卡密
     * @return 结果
     */
    public int updateCardKey(CardKey cardKey);

    /**
     * 批量删除卡密
     *
     * @param cardKeyIds 需要删除的卡密主键集合
     * @return 结果
     */
    public int deleteCardKeyByCardKeyIds(Long[] cardKeyIds);

    /**
     * 删除卡密信息
     *
     * @param cardKeyId 卡密主键
     * @return 结果
     */
    public int deleteCardKeyByCardKeyId(Long cardKeyId);


    public int batchInsertCardKey(GenerateCardReqBody generateCardReqBody);
}
