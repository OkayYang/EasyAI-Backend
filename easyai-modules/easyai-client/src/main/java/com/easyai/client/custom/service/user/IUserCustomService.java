package com.easyai.client.custom.service.user;

import com.easyai.client.custom.controller.unauth.vo.LoginReqBody;
import com.easyai.client.custom.controller.unauth.vo.RegisterReqBody;
import com.easyai.client.custom.controller.unauth.vo.ResetPasswordReqBody;
import com.easyai.client.custom.controller.user.vo.UserInfoRespBody;
import com.easyai.client.custom.model.EasyAILoginUser;
import com.easyai.client.base.domain.User;

public interface IUserCustomService {
    /**
     * 用户登陆
     *
     * @param loginReqBody 用户登陆请求body
     * @return 用户
     */
    public EasyAILoginUser login (LoginReqBody loginReqBody);

    /**
     * 用户注册
     * @param registerReqBody 注册信息
     * @param inviteUid 邀请码
     */
    public User register(RegisterReqBody registerReqBody, String inviteUid);

    /**
     * 获取用户登陆信息
     * @param userId 用户id
     * @return 用户信息VO
     */
    public UserInfoRespBody getUserInfo(Long userId);

    /**
     * 忘记密码
     */
    public void resetPassword(ResetPasswordReqBody resetPasswordReqBody);


}
