package com.easyai.client.custom.service.inviteUser;

import com.easyai.client.custom.controller.invite.vo.InviteListRespBody;
import com.easyai.client.custom.mapper.UserInviteLogCustomMapper;
import com.easyai.client.base.domain.UserInviteLog;
import com.easyai.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/22  19:56
 */
@Service
public class InviteUserCustomService implements IInviteUserCustomService {

    @Autowired
    private UserInviteLogCustomMapper userInviteLogCustomMapper;

    @Override
    public List<InviteListRespBody> getInviteUserList() {
        String email = SecurityUtils.getUsername();
        List<UserInviteLog> userInviteLogs = userInviteLogCustomMapper.selectUserInviteList(email);
        List<InviteListRespBody> inviteListRespBodyList = new ArrayList<>();

        for (UserInviteLog userInviteLog : userInviteLogs) {
            InviteListRespBody respBody = new InviteListRespBody();
            BeanUtils.copyProperties(userInviteLog, respBody);
            inviteListRespBodyList.add(respBody);
        }
        BeanUtils.copyProperties(userInviteLogs,inviteListRespBodyList);
        return inviteListRespBodyList;
    }
}
