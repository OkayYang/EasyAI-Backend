package com.easyai.client.base.service.impl;

import java.util.List;

import com.easyai.client.base.domain.UserCardLog;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.UserCardLogMapper;
import com.easyai.client.base.service.IUserCardLogService;

/**
 * 用户卡密日志Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
@Service
public class UserCardLogServiceImpl implements IUserCardLogService
{
    @Autowired
    private UserCardLogMapper userCardLogMapper;

    /**
     * 查询用户卡密日志
     *
     * @param userCardId 用户卡密日志主键
     * @return 用户卡密日志
     */
    @Override
    public UserCardLog selectUserCardLogByUserCardId(Long userCardId)
    {
        return userCardLogMapper.selectUserCardLogByUserCardId(userCardId);
    }

    /**
     * 查询用户卡密日志列表
     *
     * @param userCardLog 用户卡密日志
     * @return 用户卡密日志
     */
    @Override
    public List<UserCardLog> selectUserCardLogList(UserCardLog userCardLog)
    {
        return userCardLogMapper.selectUserCardLogList(userCardLog);
    }

    /**
     * 新增用户卡密日志
     *
     * @param userCardLog 用户卡密日志
     * @return 结果
     */
    @Override
    public int insertUserCardLog(UserCardLog userCardLog)
    {
        userCardLog.setCreateTime(DateUtils.getNowDate());
        return userCardLogMapper.insertUserCardLog(userCardLog);
    }

    /**
     * 修改用户卡密日志
     *
     * @param userCardLog 用户卡密日志
     * @return 结果
     */
    @Override
    public int updateUserCardLog(UserCardLog userCardLog)
    {
        return userCardLogMapper.updateUserCardLog(userCardLog);
    }

    /**
     * 批量删除用户卡密日志
     *
     * @param userCardIds 需要删除的用户卡密日志主键
     * @return 结果
     */
    @Override
    public int deleteUserCardLogByUserCardIds(Long[] userCardIds)
    {
        return userCardLogMapper.deleteUserCardLogByUserCardIds(userCardIds);
    }

    /**
     * 删除用户卡密日志信息
     *
     * @param userCardId 用户卡密日志主键
     * @return 结果
     */
    @Override
    public int deleteUserCardLogByUserCardId(Long userCardId)
    {
        return userCardLogMapper.deleteUserCardLogByUserCardId(userCardId);
    }
}
