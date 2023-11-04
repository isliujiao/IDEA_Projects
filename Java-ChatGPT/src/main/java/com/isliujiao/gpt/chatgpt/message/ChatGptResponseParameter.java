package com.isliujiao.gpt.chatgpt.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 厚积薄发
 * @create 2023-06-06-16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptResponseParameter {

    String id;
    String object;
    String created;
    String model;
    Usage usage;
    List<Choices> choices;
}

