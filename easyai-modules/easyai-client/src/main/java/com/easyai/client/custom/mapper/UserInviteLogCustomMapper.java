package com.easyai.client.custom.mapper;

import com.easyai.client.base.domain.UserInviteLog;
import com.easyai.client.base.mapper.UserInviteLogMapper;

import java.util.List;

public interface UserInviteLogCustomMapper extends UserInviteLogMapper {
    List<UserInviteLog> selectUserInviteList(String invite_email);
}
