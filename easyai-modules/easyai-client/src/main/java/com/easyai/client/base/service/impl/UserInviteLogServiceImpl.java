package com.easyai.client.base.service.impl;

import java.util.List;

import com.easyai.client.base.domain.UserInviteLog;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.UserInviteLogMapper;
import com.easyai.client.base.service.IUserInviteLogService;

/**
 * 用户邀请日志Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
@Service
public class UserInviteLogServiceImpl implements IUserInviteLogService
{
    @Autowired
    private UserInviteLogMapper userInviteLogMapper;

    /**
     * 查询用户邀请日志
     *
     * @param inviteId 用户邀请日志主键
     * @return 用户邀请日志
     */
    @Override
    public UserInviteLog selectUserInviteLogByInviteId(Long inviteId)
    {
        return userInviteLogMapper.selectUserInviteLogByInviteId(inviteId);
    }

    /**
     * 查询用户邀请日志列表
     *
     * @param userInviteLog 用户邀请日志
     * @return 用户邀请日志
     */
    @Override
    public List<UserInviteLog> selectUserInviteLogList(UserInviteLog userInviteLog)
    {
        return userInviteLogMapper.selectUserInviteLogList(userInviteLog);
    }

    /**
     * 新增用户邀请日志
     *
     * @param userInviteLog 用户邀请日志
     * @return 结果
     */
    @Override
    public int insertUserInviteLog(UserInviteLog userInviteLog)
    {
        userInviteLog.setCreateTime(DateUtils.getNowDate());
        return userInviteLogMapper.insertUserInviteLog(userInviteLog);
    }

    /**
     * 修改用户邀请日志
     *
     * @param userInviteLog 用户邀请日志
     * @return 结果
     */
    @Override
    public int updateUserInviteLog(UserInviteLog userInviteLog)
    {
        return userInviteLogMapper.updateUserInviteLog(userInviteLog);
    }

    /**
     * 批量删除用户邀请日志
     *
     * @param inviteIds 需要删除的用户邀请日志主键
     * @return 结果
     */
    @Override
    public int deleteUserInviteLogByInviteIds(Long[] inviteIds)
    {
        return userInviteLogMapper.deleteUserInviteLogByInviteIds(inviteIds);
    }

    /**
     * 删除用户邀请日志信息
     *
     * @param inviteId 用户邀请日志主键
     * @return 结果
     */
    @Override
    public int deleteUserInviteLogByInviteId(Long inviteId)
    {
        return userInviteLogMapper.deleteUserInviteLogByInviteId(inviteId);
    }
}
