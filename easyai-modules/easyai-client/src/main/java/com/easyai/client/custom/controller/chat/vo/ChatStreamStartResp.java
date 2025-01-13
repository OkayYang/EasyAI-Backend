package com.easyai.client.custom.controller.chat.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author XuShiPing
 * @since 2024/12/28  12:15
 */
@Data
@AllArgsConstructor
public class ChatStreamStartResp {
    String parent_id;
    String user_message_id;
    String ai_message_id;
    String model_name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

}
