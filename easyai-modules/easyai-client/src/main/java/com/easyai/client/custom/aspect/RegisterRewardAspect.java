package com.easyai.client.custom.aspect;

import com.easyai.client.custom.controller.unauth.vo.RegisterReqBody;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.base.domain.RewardConfig;
import com.easyai.client.base.domain.User;
import com.easyai.client.base.mapper.RewardConfigMapper;
import com.easyai.client.custom.enums.RewardConfigStatusEnum;
import com.easyai.common.core.exception.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.easyai.client.custom.constant.EasyAIConstants.REGISTER_REWARD_TAG;

/**
 * @author XuShiPing
 * @since 2024/10/20  19:26
 */

@Aspect
@Component
public class RegisterRewardAspect {

    @Autowired
    private RewardConfigMapper rewardConfigMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;


    /*
         注册奖励
     */
    @AfterReturning(value = "@annotation(com.easyai.client.custom.annotation.RegisterReward) " +
            "&& args(registerReqBody, inviteUid)", returning = "newUser",
            argNames = "joinPoint,registerReqBody,inviteUid,newUser")
    public void handleRegisterReward(JoinPoint joinPoint, RegisterReqBody registerReqBody, String inviteUid, User newUser) {
        RewardConfig rewardConfig = rewardConfigMapper.selectRewardConfigByName(REGISTER_REWARD_TAG);
        if (rewardConfig == null) {
            throw new ServiceException("注册配置未查询到!请联系管理员!");
        }
        // 未开启注册奖励直接跳过
        if (RewardConfigStatusEnum.CONFIG_OPEN.getValue().equals(rewardConfig.getStatus())) {
            userCustomMapper.addUserPowerById(newUser.getId(), rewardConfig.getReward());
        }

    }
}


