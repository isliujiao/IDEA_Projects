package com.isliujiao.gpt.chatgpt.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 厚积薄发
 * @create 2023-06-06-16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choices {

    ChatGptMessage message;
    String finish_reason;
    Integer index;
}

