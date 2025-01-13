package com.easyai.client.base.service.impl;

import java.util.List;

import com.easyai.client.base.domain.RewardConfig;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.RewardConfigMapper;
import com.easyai.client.base.service.IRewardConfigService;

/**
 * 奖励配置Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-10-20
 */
@Service
public class RewardConfigServiceImpl implements IRewardConfigService
{
    @Autowired
    private RewardConfigMapper rewardConfigMapper;

    /**
     * 查询奖励配置
     *
     * @param rewardConfigId 奖励配置主键
     * @return 奖励配置
     */
    @Override
    public RewardConfig selectRewardConfigByRewardConfigId(Long rewardConfigId)
    {
        return rewardConfigMapper.selectRewardConfigByRewardConfigId(rewardConfigId);
    }

    /**
     * 查询奖励配置列表
     *
     * @param rewardConfig 奖励配置
     * @return 奖励配置
     */
    @Override
    public List<RewardConfig> selectRewardConfigList(RewardConfig rewardConfig)
    {
        return rewardConfigMapper.selectRewardConfigList(rewardConfig);
    }

    /**
     * 新增奖励配置
     *
     * @param rewardConfig 奖励配置
     * @return 结果
     */
    @Override
    public int insertRewardConfig(RewardConfig rewardConfig)
    {
        rewardConfig.setCreateTime(DateUtils.getNowDate());
        return rewardConfigMapper.insertRewardConfig(rewardConfig);
    }

    /**
     * 修改奖励配置
     *
     * @param rewardConfig 奖励配置
     * @return 结果
     */
    @Override
    public int updateRewardConfig(RewardConfig rewardConfig)
    {
        rewardConfig.setUpdateTime(DateUtils.getNowDate());
        return rewardConfigMapper.updateRewardConfig(rewardConfig);
    }

    /**
     * 批量删除奖励配置
     *
     * @param rewardConfigIds 需要删除的奖励配置主键
     * @return 结果
     */
    @Override
    public int deleteRewardConfigByRewardConfigIds(Long[] rewardConfigIds)
    {
        return rewardConfigMapper.deleteRewardConfigByRewardConfigIds(rewardConfigIds);
    }

    /**
     * 删除奖励配置信息
     *
     * @param rewardConfigId 奖励配置主键
     * @return 结果
     */
    @Override
    public int deleteRewardConfigByRewardConfigId(Long rewardConfigId)
    {
        return rewardConfigMapper.deleteRewardConfigByRewardConfigId(rewardConfigId);
    }
}
