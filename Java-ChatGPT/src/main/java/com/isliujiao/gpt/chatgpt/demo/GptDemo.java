package com.isliujiao.gpt.chatgpt.demo;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

/**
 * @author liujiao
 * @date 2023/11/4 12:47
 */
public class GptDemo {

    private static String openAiApiKey = "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";

    public static void main(String[] args) {
        String jsonStr = JSONUtil.toJsonStr("{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"system\",\n" +
                "        \"content\": \"You are a helpful assistant.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "        \"content\": \"Hello!\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }");
        String result = HttpRequest.post("https://api.openai.com/v1/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer" + openAiApiKey)
                .body(jsonStr)
                .setConnectionTimeout(100000)
                .execute()
                .body();

        System.out.println(result);
    }

}
