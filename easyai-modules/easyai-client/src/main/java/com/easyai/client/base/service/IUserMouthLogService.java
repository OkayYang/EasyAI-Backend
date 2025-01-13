package com.easyai.client.base.service;

import java.util.List;

import com.easyai.client.base.domain.UserMouthLog;

/**
 * 用户口令日志Service接口
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
public interface IUserMouthLogService
{
    /**
     * 查询用户口令日志
     *
     * @param mouthUserId 用户口令日志主键
     * @return 用户口令日志
     */
    public UserMouthLog selectUserMouthLogByMouthUserId(Long mouthUserId);

    /**
     * 查询用户口令日志列表
     *
     * @param userMouthLog 用户口令日志
     * @return 用户口令日志集合
     */
    public List<UserMouthLog> selectUserMouthLogList(UserMouthLog userMouthLog);

    /**
     * 新增用户口令日志
     *
     * @param userMouthLog 用户口令日志
     * @return 结果
     */
    public int insertUserMouthLog(UserMouthLog userMouthLog);

    /**
     * 修改用户口令日志
     *
     * @param userMouthLog 用户口令日志
     * @return 结果
     */
    public int updateUserMouthLog(UserMouthLog userMouthLog);

    /**
     * 批量删除用户口令日志
     *
     * @param mouthUserIds 需要删除的用户口令日志主键集合
     * @return 结果
     */
    public int deleteUserMouthLogByMouthUserIds(Long[] mouthUserIds);

    /**
     * 删除用户口令日志信息
     *
     * @param mouthUserId 用户口令日志主键
     * @return 结果
     */
    public int deleteUserMouthLogByMouthUserId(Long mouthUserId);
}
