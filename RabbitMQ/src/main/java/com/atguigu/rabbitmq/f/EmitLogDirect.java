package com.atguigu.rabbitmq.f;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**直接交换机：路由模式（根据routingKey的不同，从而交换机会转发给对应的消费者）
 * @author:厚积薄发
 * @create:2022-08-18-23:34
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";//交换机名称

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息" + message);
        }
    }

}
