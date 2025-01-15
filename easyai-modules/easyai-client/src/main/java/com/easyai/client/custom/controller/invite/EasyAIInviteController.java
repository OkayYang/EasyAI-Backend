package com.easyai.client.custom.controller.invite;

import com.easyai.client.custom.controller.invite.vo.InviteListRespBody;
import com.easyai.client.custom.service.inviteUser.InviteUserCustomService;
import com.easyai.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  16:20
 */
@RestController
@RequestMapping("/easyai/invite")
public class EasyAIInviteController {

    @Autowired
    private InviteUserCustomService inviteUserCustomService;

    @GetMapping("/list")
    public AjaxResult getInviteUserList() {
        List<InviteListRespBody> inviteUserList = inviteUserCustomService.getInviteUserList();
        return AjaxResult.success(inviteUserList);
    }

}
