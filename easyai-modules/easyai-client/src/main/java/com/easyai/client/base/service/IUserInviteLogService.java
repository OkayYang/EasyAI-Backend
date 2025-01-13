package com.easyai.client.base.service;

import java.util.List;

import com.easyai.client.base.domain.UserInviteLog;

/**
 * 用户邀请日志Service接口
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
public interface IUserInviteLogService
{
    /**
     * 查询用户邀请日志
     *
     * @param inviteId 用户邀请日志主键
     * @return 用户邀请日志
     */
    public UserInviteLog selectUserInviteLogByInviteId(Long inviteId);

    /**
     * 查询用户邀请日志列表
     *
     * @param userInviteLog 用户邀请日志
     * @return 用户邀请日志集合
     */
    public List<UserInviteLog> selectUserInviteLogList(UserInviteLog userInviteLog);

    /**
     * 新增用户邀请日志
     *
     * @param userInviteLog 用户邀请日志
     * @return 结果
     */
    public int insertUserInviteLog(UserInviteLog userInviteLog);

    /**
     * 修改用户邀请日志
     *
     * @param userInviteLog 用户邀请日志
     * @return 结果
     */
    public int updateUserInviteLog(UserInviteLog userInviteLog);

    /**
     * 批量删除用户邀请日志
     *
     * @param inviteIds 需要删除的用户邀请日志主键集合
     * @return 结果
     */
    public int deleteUserInviteLogByInviteIds(Long[] inviteIds);

    /**
     * 删除用户邀请日志信息
     *
     * @param inviteId 用户邀请日志主键
     * @return 结果
     */
    public int deleteUserInviteLogByInviteId(Long inviteId);
}
