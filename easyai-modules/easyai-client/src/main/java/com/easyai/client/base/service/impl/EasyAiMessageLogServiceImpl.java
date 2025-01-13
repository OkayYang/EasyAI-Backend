package com.easyai.client.base.service.impl;

import java.util.List;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.EasyAiMessageLogMapper;
import com.easyai.client.base.domain.EasyAiMessageLog;
import com.easyai.client.base.service.IEasyAiMessageLogService;

/**
 * 消息日志Service业务层处理
 *
 * @author xiaoshuaiage
 * @date 2024-12-25
 */
@Service
public class EasyAiMessageLogServiceImpl implements IEasyAiMessageLogService
{
    @Autowired
    private EasyAiMessageLogMapper easyAiMessageLogMapper;

    /**
     * 查询消息日志
     *
     * @param messageLogId 消息日志主键
     * @return 消息日志
     */
    @Override
    public EasyAiMessageLog selectEasyAiMessageLogByMessageLogId(String messageLogId)
    {
        return easyAiMessageLogMapper.selectEasyAiMessageLogByMessageLogId(messageLogId);
    }

    /**
     * 查询消息日志列表
     *
     * @param easyAiMessageLog 消息日志
     * @return 消息日志
     */
    @Override
    public List<EasyAiMessageLog> selectEasyAiMessageLogList(EasyAiMessageLog easyAiMessageLog)
    {
        return easyAiMessageLogMapper.selectEasyAiMessageLogList(easyAiMessageLog);
    }

    /**
     * 新增消息日志
     *
     * @param easyAiMessageLog 消息日志
     * @return 结果
     */
    @Override
    public int insertEasyAiMessageLog(EasyAiMessageLog easyAiMessageLog)
    {
        easyAiMessageLog.setCreateTime(DateUtils.getNowDate());
        return easyAiMessageLogMapper.insertEasyAiMessageLog(easyAiMessageLog);
    }

    /**
     * 修改消息日志
     *
     * @param easyAiMessageLog 消息日志
     * @return 结果
     */
    @Override
    public int updateEasyAiMessageLog(EasyAiMessageLog easyAiMessageLog)
    {
        easyAiMessageLog.setUpdateTime(DateUtils.getNowDate());
        return easyAiMessageLogMapper.updateEasyAiMessageLog(easyAiMessageLog);
    }

    /**
     * 批量删除消息日志
     *
     * @param messageLogIds 需要删除的消息日志主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageLogByMessageLogIds(String[] messageLogIds)
    {
        return easyAiMessageLogMapper.deleteEasyAiMessageLogByMessageLogIds(messageLogIds);
    }

    /**
     * 删除消息日志信息
     *
     * @param messageLogId 消息日志主键
     * @return 结果
     */
    @Override
    public int deleteEasyAiMessageLogByMessageLogId(String messageLogId)
    {
        return easyAiMessageLogMapper.deleteEasyAiMessageLogByMessageLogId(messageLogId);
    }
}
