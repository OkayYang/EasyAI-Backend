package com.easyai.client.base.mapper;

import java.util.List;

import com.easyai.client.base.domain.UserCardLog;

/**
 * 用户卡密日志Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
public interface UserCardLogMapper
{
    /**
     * 查询用户卡密日志
     *
     * @param userCardId 用户卡密日志主键
     * @return 用户卡密日志
     */
    public UserCardLog selectUserCardLogByUserCardId(Long userCardId);

    /**
     * 查询用户卡密日志列表
     *
     * @param userCardLog 用户卡密日志
     * @return 用户卡密日志集合
     */
    public List<UserCardLog> selectUserCardLogList(UserCardLog userCardLog);

    /**
     * 新增用户卡密日志
     *
     * @param userCardLog 用户卡密日志
     * @return 结果
     */
    public int insertUserCardLog(UserCardLog userCardLog);

    /**
     * 修改用户卡密日志
     *
     * @param userCardLog 用户卡密日志
     * @return 结果
     */
    public int updateUserCardLog(UserCardLog userCardLog);

    /**
     * 删除用户卡密日志
     *
     * @param userCardId 用户卡密日志主键
     * @return 结果
     */
    public int deleteUserCardLogByUserCardId(Long userCardId);

    /**
     * 批量删除用户卡密日志
     *
     * @param userCardIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCardLogByUserCardIds(Long[] userCardIds);
}
