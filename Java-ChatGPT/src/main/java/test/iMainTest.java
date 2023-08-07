package test;

//import com.example.chatgpt.controller.CustomChatGpt;
import controller.CustomChatGpt;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author 厚积薄发
 * @create 2023-06-06-16:10
 */
public class iMainTest {
    @Test
    public void test() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String apiKey = "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";
        CustomChatGpt customChatGpt = new CustomChatGpt(apiKey);
        // 根据自己的网络设置吧
        customChatGpt.setResponseTimeout(20000);
        while (true) {
            System.out.print("\n请输入问题(q退出)：");
            String question = new Scanner(System.in).nextLine();
            if ("q".equals(question)){
                break;
            }
            long start = System.currentTimeMillis();
            String answer = customChatGpt.getAnswer(httpClient, question);
            long end = System.currentTimeMillis();
            System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");
            System.out.println(answer);
        }
        httpClient.close();
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String apiKey = "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";
        CustomChatGpt customChatGpt = new CustomChatGpt(apiKey);
        // 根据自己的网络设置吧
        customChatGpt.setResponseTimeout(20000);
        while (true) {
            System.out.print("\n请输入问题(q退出)：");
            String question = new Scanner(System.in).nextLine();
            if ("q".equals(question)){
                break;
            }
            long start = System.currentTimeMillis();
            String answer = customChatGpt.getAnswer(httpClient, question);
            long end = System.currentTimeMillis();
            System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");
            System.out.println(answer);
        }
        httpClient.close();
    }
}
