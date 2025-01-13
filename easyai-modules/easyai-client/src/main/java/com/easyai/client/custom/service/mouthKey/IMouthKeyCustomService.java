package com.easyai.client.custom.service.mouthKey;

import com.easyai.client.custom.controller.mouth.vo.MouthKeyUsedListRespBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardReqBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardRespBody;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/21  15:24
 */
public interface IMouthKeyCustomService {

    /**
     * 使用口令记录
     *
     * @param mouthRewardReqBody
     */
    public MouthRewardRespBody useMouthKey(MouthRewardReqBody mouthRewardReqBody);

    public List<MouthKeyUsedListRespBody> getMouthKeyUsedList();
}
