package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.EasyAiMessageErrorLogMapper;
import com.easyai.client.base.domain.EasyAiMessageErrorLog;
import com.easyai.client.base.service.IEasyAiMessageErrorLogService;

/**
 * 消息异常日志Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-12-29
 */
@Service
public class EasyAiMessageErrorLogServiceImpl implements IEasyAiMessageErrorLogService
{
    @Autowired
    private EasyAiMessageErrorLogMapper easyAiMessageErrorLogMapper;

    /**
     * 查询消息异常日志
     *
     * @param messageErrorLogId 消息异常日志主键
     * @return 消息异常日志
     */
    @Override
    public EasyAiMessageErrorLog selectEasyAiMessageErrorLogByMessageErrorLogId(int messageErrorLogId)
    {
        return easyAiMessageErrorLogMapper.selectEasyAiMessageErrorLogByMessageErrorLogId(messageErrorLogId);
    }

    /**
     * 查询消息异常日志列表
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 消息异常日志
     */
    @Override
    public List<EasyAiMessageErrorLog> selectEasyAiMessageErrorLogList(EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        return easyAiMessageErrorLogMapper.selectEasyAiMessageErrorLogList(easyAiMessageErrorLog);
    }

    /**
     * 新增消息异常日志
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 结果
     */
    @Override
    public int insertEasyAiMessageErrorLog(EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        easyAiMessageErrorLog.setCreateTime(DateUtils.getNowDate());
        return easyAiMessageErrorLogMapper.insertEasyAiMessageErrorLog(easyAiMessageErrorLog);
    }

    /**
     * 修改消息异常日志
     *
     * @param easyAiMessageErrorLog 消息异常日志
     * @return 结果
     */
    @Override
    public int updateEasyAiMessageErrorLog(EasyAiMessageErrorLog easyAiMessageErrorLog)
    {
        easyAiMessageErrorLog.setUpdateTime(DateUtils.getNowDate());
        return easyAiMessageErrorLogMapper.updateEasyAiMessageErrorLog(easyAiMessageErrorLog);
    }

    /**
     * 批量删除消息异常日志
     *
     * @param messageErrorLogIds 需要删除的消息异常日志主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageErrorLogByMessageErrorLogIds(int[] messageErrorLogIds)
    {
        return easyAiMessageErrorLogMapper.deleteEasyAiMessageErrorLogByMessageErrorLogIds(messageErrorLogIds);
    }

    /**
     * 删除消息异常日志信息
     *
     * @param messageErrorLogId 消息异常日志主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageErrorLogByMessageErrorLogId(int messageErrorLogId)
    {
        return easyAiMessageErrorLogMapper.deleteEasyAiMessageErrorLogByMessageErrorLogId(messageErrorLogId);
    }
}
