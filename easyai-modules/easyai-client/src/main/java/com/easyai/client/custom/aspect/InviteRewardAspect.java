package com.easyai.client.custom.aspect;


import com.easyai.client.custom.controller.unauth.vo.RegisterReqBody;
import com.easyai.client.base.domain.RewardConfig;
import com.easyai.client.base.domain.User;
import com.easyai.client.base.domain.UserInviteLog;
import com.easyai.client.base.mapper.RewardConfigMapper;
import com.easyai.client.base.mapper.UserInviteLogMapper;
import com.easyai.client.custom.enums.RewardConfigStatusEnum;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.common.core.constant.CacheConstants;
import com.easyai.common.core.exception.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.easyai.client.custom.constant.EasyAIConstants.INVITE_REWARD_TAG;


/**
 * @author XuShiPing
 * @since 2024/10/20  19:26
 */

@Aspect
@Component
public class InviteRewardAspect {

    @Autowired
    private RewardConfigMapper rewardConfigMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Autowired
    private UserInviteLogMapper userInviteLogMapper;


    /*
         邀请奖励 和 邀请记录
     */
    @AfterReturning(value = "@annotation(com.easyai.client.custom.annotation.InviteReward) " +
            "&& args(registerReqBody, inviteUid)", returning = "newUser",
            argNames = "joinPoint,registerReqBody,inviteUid,newUser")
    public void handleInviteReward(JoinPoint joinPoint, RegisterReqBody registerReqBody, String inviteUid, User newUser) {
        if (inviteUid != null) {
            try {
                long id = Long.parseLong(inviteUid);
                RewardConfig rewardConfig = rewardConfigMapper.selectRewardConfigByName(INVITE_REWARD_TAG);

                if (rewardConfig == null) {
                    throw new ServiceException("邀请配置未查询到!请联系管理员!");
                }
                // 邀请配置状态
                String status = rewardConfig.getStatus();
                boolean isOpen = RewardConfigStatusEnum.CONFIG_OPEN.getValue().equals(status);
                // 算力奖励
                Long reward = rewardConfig.getReward();
                // 邀请人信息
                User rewardedUser = userCustomMapper.selectUserById(id);
                // 是否存在邀请人
                boolean hasInviteUser = rewardedUser != null;

                // 邀请记录
                if (hasInviteUser) {
                    UserInviteLog inviteUserLog = new UserInviteLog();
                    inviteUserLog.setInviteEmail(rewardedUser.getUsername());
                    inviteUserLog.setInvitedEmail(newUser.getUsername());
                    if (isOpen) {
                        inviteUserLog.setReward(reward);
                    }
                    userInviteLogMapper.insertUserInviteLog(inviteUserLog);
                }
                // 判断邀请奖励状态是否开启 未开启直接结束
                if (isOpen && hasInviteUser) {
                    userCustomMapper.addUserPowerById(id, reward);
                }

            } catch (NumberFormatException e) {
                System.out.println("邀请码错误");
            }
        }
    }
}
//

