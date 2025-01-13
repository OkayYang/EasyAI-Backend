package com.easyai.client.base.service;

import java.util.List;

import com.easyai.client.base.domain.RewardConfig;

/**
 * 奖励配置Service接口
 *
 * @author xiaoshuaige
 * @date 2024-10-20
 */
public interface IRewardConfigService
{
    /**
     * 查询奖励配置
     *
     * @param rewardConfigId 奖励配置主键
     * @return 奖励配置
     */
    public RewardConfig selectRewardConfigByRewardConfigId(Long rewardConfigId);

    /**
     * 查询奖励配置列表
     *
     * @param rewardConfig 奖励配置
     * @return 奖励配置集合
     */
    public List<RewardConfig> selectRewardConfigList(RewardConfig rewardConfig);

    /**
     * 新增奖励配置
     *
     * @param rewardConfig 奖励配置
     * @return 结果
     */
    public int insertRewardConfig(RewardConfig rewardConfig);

    /**
     * 修改奖励配置
     *
     * @param rewardConfig 奖励配置
     * @return 结果
     */
    public int updateRewardConfig(RewardConfig rewardConfig);

    /**
     * 批量删除奖励配置
     *
     * @param rewardConfigIds 需要删除的奖励配置主键集合
     * @return 结果
     */
    public int deleteRewardConfigByRewardConfigIds(Long[] rewardConfigIds);

    /**
     * 删除奖励配置信息
     *
     * @param rewardConfigId 奖励配置主键
     * @return 结果
     */
    public int deleteRewardConfigByRewardConfigId(Long rewardConfigId);
}
