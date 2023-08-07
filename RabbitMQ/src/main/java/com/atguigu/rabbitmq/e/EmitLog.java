package com.atguigu.rabbitmq.e;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**扇出交换机：发布订阅模式（一次发送由多个交换机转发到多个信道，由多个消费者消费同一次消息）
 * @author:厚积薄发
 * @create:2022-08-18-13:06
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";//交换机名称

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        /**
         * 声明一个 exchange(交换机)
         * 1.exchange 的名称
         * 2.exchange 的类型
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入信息");
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息" + message);
        }
    }

}
