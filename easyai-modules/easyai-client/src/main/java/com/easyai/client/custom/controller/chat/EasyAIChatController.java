package com.easyai.client.custom.controller.chat;

import com.easyai.client.base.domain.ApiKey;
import com.easyai.client.base.domain.EasyAiMessage;
import com.easyai.client.base.service.IChatService;
import com.easyai.client.custom.controller.chat.vo.ChatListRespBody;
import com.easyai.client.custom.controller.chat.vo.ChatStreamReqBody;
import com.easyai.client.custom.controller.chat.vo.ChatStreamResp;
import com.easyai.client.custom.controller.chat.vo.EditTitleReqBody;
import com.easyai.client.custom.mapper.ApiKeyCustomMapper;
import com.easyai.client.custom.mapper.EasyAiMessageCustomMapper;
import com.easyai.client.custom.service.apikey.ApiKeyCustomService;
import com.easyai.client.custom.service.chat.IChatCustomService;
import com.easyai.common.core.web.controller.BaseController;
import com.easyai.common.core.web.domain.AjaxResult;
import org.apache.ibatis.annotations.Delete;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author XuShiPing
 * @since 2024/12/17  21:29
 */
@RestController
@RequestMapping("/easyai/conversation")
public class EasyAIChatController extends BaseController {

    @Autowired
    private IChatCustomService chatCustomService;


    @GetMapping("/list")
    public AjaxResult getChatList() {
        List<ChatListRespBody> inviteUserList = chatCustomService.listAllChat();
        return AjaxResult.success(inviteUserList);
    }
    @DeleteMapping("/{session_id}")
    public AjaxResult deleteChat(@PathVariable String session_id){
        chatCustomService.deleteChat(session_id);
        return AjaxResult.success();
    }
    @PatchMapping
    public AjaxResult editChatTitle( @RequestBody EditTitleReqBody editTitleReqBody) {
        return toAjax(chatCustomService.updateChatTitle(editTitleReqBody));

    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatStreamResp<?>> chat(@RequestBody ChatStreamReqBody chatStreamReqBody) {
        return chatCustomService.chat(chatStreamReqBody);
    }


}
