package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.MouthKey;
import com.easyai.client.base.mapper.MouthKeyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author XuShiPing
 * @since 2024/10/22  15:33
 */
public interface MouthKeyCustomMapper extends MouthKeyMapper {

    MouthKey getMouthKeyByCategory(@Param("category") String category);


}
