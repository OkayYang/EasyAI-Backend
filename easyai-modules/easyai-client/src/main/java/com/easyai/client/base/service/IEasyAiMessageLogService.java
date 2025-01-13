package com.easyai.client.base.service;

import java.util.List;
import com.easyai.client.base.domain.EasyAiMessageLog;

/**
 * 消息日志Service接口
 *
 * @author xiaoshuaiage
 * @date 2024-12-25
 */
public interface IEasyAiMessageLogService
{
    /**
     * 查询消息日志
     *
     * @param messageLogId 消息日志主键
     * @return 消息日志
     */
    public EasyAiMessageLog selectEasyAiMessageLogByMessageLogId(String messageLogId);

    /**
     * 查询消息日志列表
     *
     * @param easyAiMessageLog 消息日志
     * @return 消息日志集合
     */
    public List<EasyAiMessageLog> selectEasyAiMessageLogList(EasyAiMessageLog easyAiMessageLog);

    /**
     * 新增消息日志
     *
     * @param easyAiMessageLog 消息日志
     * @return 结果
     */
    public int insertEasyAiMessageLog(EasyAiMessageLog easyAiMessageLog);

    /**
     * 修改消息日志
     *
     * @param easyAiMessageLog 消息日志
     * @return 结果
     */
    public int updateEasyAiMessageLog(EasyAiMessageLog easyAiMessageLog);

    /**
     * 批量删除消息日志
     *
     * @param messageLogIds 需要删除的消息日志主键集合
     * @return 结果
     */
    public int deleteEasyAiMessageLogByMessageLogIds(String[] messageLogIds);

    /**
     * 删除消息日志信息
     *
     * @param messageLogId 消息日志主键
     * @return 结果
     */
    public int deleteEasyAiMessageLogByMessageLogId(String messageLogId);
}
