package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.UserCardLog;
import com.easyai.client.base.mapper.UserCardLogMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户卡密日志Mapper接口
 *
 * @author xiaoshuaige
 * @date 2024-10-21
 */
public interface UserCardLogCustomMapper extends UserCardLogMapper {

    List<UserCardLog> selectUserCardUsedList(@Param("email") String email);
}
