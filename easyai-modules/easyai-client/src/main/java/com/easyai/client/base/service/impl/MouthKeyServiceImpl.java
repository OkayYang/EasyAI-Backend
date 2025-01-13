package com.easyai.client.base.service.impl;

import java.util.List;

import com.easyai.client.base.domain.MouthKey;
import com.easyai.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyai.client.base.mapper.MouthKeyMapper;
import com.easyai.client.base.service.IMouthKeyService;

/**
 * 口令管理Service业务层处理
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
@Service
public class MouthKeyServiceImpl implements IMouthKeyService
{
    @Autowired
    private MouthKeyMapper mouthKeyMapper;

    /**
     * 查询口令管理
     *
     * @param mouthKeyId 口令管理主键
     * @return 口令管理
     */
    @Override
    public MouthKey selectMouthKeyByMouthKeyId(Long mouthKeyId)
    {
        return mouthKeyMapper.selectMouthKeyByMouthKeyId(mouthKeyId);
    }

    /**
     * 查询口令管理列表
     *
     * @param mouthKey 口令管理
     * @return 口令管理
     */
    @Override
    public List<MouthKey> selectMouthKeyList(MouthKey mouthKey)
    {
        return mouthKeyMapper.selectMouthKeyList(mouthKey);
    }

    /**
     * 新增口令管理
     *
     * @param mouthKey 口令管理
     * @return 结果
     */
    @Override
    public int insertMouthKey(MouthKey mouthKey)
    {
        mouthKey.setCreateTime(DateUtils.getNowDate());
        return mouthKeyMapper.insertMouthKey(mouthKey);
    }

    /**
     * 修改口令管理
     *
     * @param mouthKey 口令管理
     * @return 结果
     */
    @Override
    public int updateMouthKey(MouthKey mouthKey)
    {
        return mouthKeyMapper.updateMouthKey(mouthKey);
    }

    /**
     * 批量删除口令管理
     *
     * @param mouthKeyIds 需要删除的口令管理主键
     * @return 结果
     */
    @Override
    public int deleteMouthKeyByMouthKeyIds(Long[] mouthKeyIds)
    {
        return mouthKeyMapper.deleteMouthKeyByMouthKeyIds(mouthKeyIds);
    }

    /**
     * 删除口令管理信息
     *
     * @param mouthKeyId 口令管理主键
     * @return 结果
     */
    @Override
    public int deleteMouthKeyByMouthKeyId(Long mouthKeyId)
    {
        return mouthKeyMapper.deleteMouthKeyByMouthKeyId(mouthKeyId);
    }
}
