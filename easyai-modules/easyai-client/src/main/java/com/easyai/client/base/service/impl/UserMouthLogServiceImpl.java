package com.easyai.client.base.service.impl;

import java.util.List;

import com.easyai.client.base.domain.UserMouthLog;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.UserMouthLogMapper;
import com.easyai.client.base.service.IUserMouthLogService;

/**
 * 用户口令日志Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-10-22
 */
@Service
public class UserMouthLogServiceImpl implements IUserMouthLogService
{
    @Autowired
    private UserMouthLogMapper userMouthLogMapper;

    /**
     * 查询用户口令日志
     *
     * @param mouthUserId 用户口令日志主键
     * @return 用户口令日志
     */
    @Override
    public UserMouthLog selectUserMouthLogByMouthUserId(Long mouthUserId)
    {
        return userMouthLogMapper.selectUserMouthLogByMouthUserId(mouthUserId);
    }

    /**
     * 查询用户口令日志列表
     *
     * @param userMouthLog 用户口令日志
     * @return 用户口令日志
     */
    @Override
    public List<UserMouthLog> selectUserMouthLogList(UserMouthLog userMouthLog)
    {
        return userMouthLogMapper.selectUserMouthLogList(userMouthLog);
    }

    /**
     * 新增用户口令日志
     *
     * @param userMouthLog 用户口令日志
     * @return 结果
     */
    @Override
    public int insertUserMouthLog(UserMouthLog userMouthLog)
    {
        userMouthLog.setCreateTime(DateUtils.getNowDate());
        return userMouthLogMapper.insertUserMouthLog(userMouthLog);
    }

    /**
     * 修改用户口令日志
     *
     * @param userMouthLog 用户口令日志
     * @return 结果
     */
    @Override
    public int updateUserMouthLog(UserMouthLog userMouthLog)
    {
        return userMouthLogMapper.updateUserMouthLog(userMouthLog);
    }

    /**
     * 批量删除用户口令日志
     *
     * @param mouthUserIds 需要删除的用户口令日志主键
     * @return 结果
     */
    @Override
    public int deleteUserMouthLogByMouthUserIds(Long[] mouthUserIds)
    {
        return userMouthLogMapper.deleteUserMouthLogByMouthUserIds(mouthUserIds);
    }

    /**
     * 删除用户口令日志信息
     *
     * @param mouthUserId 用户口令日志主键
     * @return 结果
     */
    @Override
    public int deleteUserMouthLogByMouthUserId(Long mouthUserId)
    {
        return userMouthLogMapper.deleteUserMouthLogByMouthUserId(mouthUserId);
    }
}
