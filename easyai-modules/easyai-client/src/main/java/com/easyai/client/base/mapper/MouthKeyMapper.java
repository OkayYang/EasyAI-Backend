package com.easyai.client.base.mapper;

import java.util.List;

import com.easyai.client.base.domain.MouthKey;

/**
 * 口令管理Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
public interface MouthKeyMapper
{
    /**
     * 查询口令管理
     *
     * @param mouthKeyId 口令管理主键
     * @return 口令管理
     */
    public MouthKey selectMouthKeyByMouthKeyId(Long mouthKeyId);

    /**
     * 查询口令管理列表
     *
     * @param mouthKey 口令管理
     * @return 口令管理集合
     */
    public List<MouthKey> selectMouthKeyList(MouthKey mouthKey);

    /**
     * 新增口令管理
     *
     * @param mouthKey 口令管理
     * @return 结果
     */
    public int insertMouthKey(MouthKey mouthKey);

    /**
     * 修改口令管理
     *
     * @param mouthKey 口令管理
     * @return 结果
     */
    public int updateMouthKey(MouthKey mouthKey);

    /**
     * 删除口令管理
     *
     * @param mouthKeyId 口令管理主键
     * @return 结果
     */
    public int deleteMouthKeyByMouthKeyId(Long mouthKeyId);

    /**
     * 批量删除口令管理
     *
     * @param mouthKeyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMouthKeyByMouthKeyIds(Long[] mouthKeyIds);
}
