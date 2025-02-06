package com.easyai.client.custom.controller.chat.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author XuShiPing
 * @since 2024/12/28  12:15
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 只序列化非 null 的字段
public class ChatStreamStartResp {
    String parent_id;
    String user_message_id;
    String ai_message_id;
    String model_name;
    String title;
    Long createTime;

}
