package com.easyai.client.base.mapper;

import java.util.List;

import com.easyai.client.base.domain.CardKey;

/**
 * 卡密Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-10-18
 */
public interface CardKeyMapper
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
     * 删除卡密
     *
     * @param cardKeyId 卡密主键
     * @return 结果
     */
    public int deleteCardKeyByCardKeyId(Long cardKeyId);

    /**
     * 批量删除卡密
     *
     * @param cardKeyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCardKeyByCardKeyIds(Long[] cardKeyIds);


    public int batchInsertCardKey(List<CardKey> cardKeyList);
}
