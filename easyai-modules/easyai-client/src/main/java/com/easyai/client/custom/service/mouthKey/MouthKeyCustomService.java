package com.easyai.client.custom.service.mouthKey;

import com.easyai.client.base.domain.MouthKey;
import com.easyai.client.base.domain.UserMouthLog;
import com.easyai.client.custom.controller.mouth.vo.MouthKeyUsedListRespBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardReqBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardRespBody;
import com.easyai.client.custom.mapper.MouthKeyCustomMapper;
import com.easyai.client.custom.mapper.UserCustomMapper;
import com.easyai.client.custom.mapper.UserMouthLogCustomMapper;
import com.easyai.client.base.domain.*;
import com.easyai.common.core.exception.ServiceException;
import com.easyai.common.core.utils.DateUtils;
import com.easyai.common.core.utils.StringUtils;
import com.easyai.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  15:24
 */

@Service
public class MouthKeyCustomService implements IMouthKeyCustomService {

    @Autowired
    private MouthKeyCustomMapper mouthKeyCustomMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Autowired
    private UserMouthLogCustomMapper userMouthLogCustomMapper;


    @Override
    public MouthRewardRespBody useMouthKey(MouthRewardReqBody mouthRewardReqBody) {
        String key = mouthRewardReqBody.getMouthKey();
        if (key == null || StringUtils.isEmpty(key)) {
            throw new ServiceException("口令不得为空!");
        }

        MouthKey mouthKeyByCategory = mouthKeyCustomMapper.getMouthKeyByCategory(key);

        if (mouthKeyByCategory == null) {
            throw new ServiceException("当前口令不存在!");
        }

        String email = SecurityUtils.getUsername();
        Long userId = SecurityUtils.getUserId();
        UserMouthLog userMouthLog = userMouthLogCustomMapper.getMouthKeyByEmailAndCategory(email, key);
        if (userMouthLog != null) {
            throw new ServiceException("你已使用过该口令啦！");
        }

        Date expireTime = mouthKeyByCategory.getExpireTime();
        Date now = new Date(System.currentTimeMillis());
        if (expireTime.before(now)) {
            throw new ServiceException("当前口令不在生效时间内！");
        }
        Long reward = mouthKeyByCategory.getReward();

        userCustomMapper.addUserPowerById(userId, reward);


        // 使用日志
        UserMouthLog newUserMouthLog = new UserMouthLog();
        newUserMouthLog.setCategory(key);
        newUserMouthLog.setReward(reward);
        newUserMouthLog.setEmail(email);
        userMouthLogCustomMapper.insertUserMouthLog(newUserMouthLog);

        // return
        MouthRewardRespBody mouthRewardRespBody = new MouthRewardRespBody();
        BeanUtils.copyProperties(mouthKeyByCategory, mouthRewardRespBody);
        return mouthRewardRespBody;
    }

    @Override
    public List<MouthKeyUsedListRespBody> getMouthKeyUsedList() {
        String email = SecurityUtils.getUsername();
        List<UserMouthLog> userMouthLogs = userMouthLogCustomMapper.selectUserMouthUsedList(email);
        List<MouthKeyUsedListRespBody> mouthKeyUsedListRespBodyList = new ArrayList<>();

        for (UserMouthLog userMouthLog : userMouthLogs) {
            MouthKeyUsedListRespBody respBody = new MouthKeyUsedListRespBody();
            BeanUtils.copyProperties(userMouthLog, respBody);
            mouthKeyUsedListRespBodyList.add(respBody);
        }

        return mouthKeyUsedListRespBodyList;
    }
}
