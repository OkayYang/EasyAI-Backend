package com.easyai.client.langchain4j.platform.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XuShiPing
 * @since 2024/12/29  15:19
 */

@Data
@AllArgsConstructor
public class OpenAiErrorMessage {
    String message;
    String type;
    String code;
}
