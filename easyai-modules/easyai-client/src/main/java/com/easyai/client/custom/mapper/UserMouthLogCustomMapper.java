package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.UserMouthLog;
import com.easyai.client.base.mapper.UserMouthLogMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMouthLogCustomMapper extends UserMouthLogMapper {

   List<UserMouthLog> selectUserMouthUsedList(String email);

    UserMouthLog getMouthKeyByEmailAndCategory(@Param("email") String email, @Param("category") String category);
}
