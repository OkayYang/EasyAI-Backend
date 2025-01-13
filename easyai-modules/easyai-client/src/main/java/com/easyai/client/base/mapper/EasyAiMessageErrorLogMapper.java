package com.easyai.client.base.mapper;

import java.util.List;
import com.easyai.client.base.domain.EasyAiMessageErrorLog;

/**
 * 消息异常日志Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-12-29
 */
public interface EasyAiMessageErrorLogMapper
{
    /**
     * 查询消息异常日志
     *
     * @param messageErrorLogId 消息异常日志主键
     * @return 消息异常日志
     */
    public EasyAiMessageErrorLog selectEasyAiMessageErrorLogByMessageErrorLogId(int messageErrorLogId);

    /**
     * 查询消息异常日志列表
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 消息异常日志集合
     */
    public List<EasyAiMessageErrorLog> selectEasyAiMessageErrorLogList(EasyAiMessageErrorLog easyAiMessageErrorLog);

    /**
     * 新增消息异常日志
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 结果
     */
    public int insertEasyAiMessageErrorLog(EasyAiMessageErrorLog easyAiMessageErrorLog);

    /**
     * 修改消息异常日志
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 结果
     */
    public int updateEasyAiMessageErrorLog(EasyAiMessageErrorLog easyAiMessageErrorLog);

    /**
     * 删除消息异常日志
     *
     * @param messageErrorLogId 消息异常日志主键
     * @return 结果
     */
    public int deleteEasyAiMessageErrorLogByMessageErrorLogId(int messageErrorLogId);

    /**
     * 批量删除消息异常日志
     *
     * @param messageErrorLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEasyAiMessageErrorLogByMessageErrorLogIds(int[] messageErrorLogIds);
}
