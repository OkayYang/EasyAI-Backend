package com.easyai.client.custom.controller.mouth;

import com.easyai.client.custom.controller.mouth.vo.MouthKeyUsedListRespBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardReqBody;
import com.easyai.client.custom.controller.mouth.vo.MouthRewardRespBody;
import com.easyai.client.custom.service.mouthKey.MouthKeyCustomService;
import com.easyai.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/10/22  15:30
 */
@RestController
@RequestMapping("/easyai/mouth")
public class EasyAIMouthController {

    @Autowired
    private MouthKeyCustomService mouthKeyCustomService;

    @PostMapping("/reward")
    public AjaxResult InputMouthKey(@RequestBody MouthRewardReqBody mouthRewardReqBody) {
        MouthRewardRespBody mouthRewardRespBody = mouthKeyCustomService.useMouthKey(mouthRewardReqBody);
        return AjaxResult.success(mouthRewardRespBody);
    }

    @GetMapping("/list")
    public AjaxResult getUsedMouthList() {
        List<MouthKeyUsedListRespBody> mouthKeyUsedList = mouthKeyCustomService.getMouthKeyUsedList();
        return AjaxResult.success(mouthKeyUsedList);
    }
}
