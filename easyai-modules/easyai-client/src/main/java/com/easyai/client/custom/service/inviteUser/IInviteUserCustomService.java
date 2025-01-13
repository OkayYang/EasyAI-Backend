package com.easyai.client.custom.service.inviteUser;

import com.easyai.client.custom.controller.invite.vo.InviteListRespBody;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  15:24
 */
public interface IInviteUserCustomService {

    List<InviteListRespBody> getInviteUserList();

}
