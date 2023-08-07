package com.atguigu.rabbitmq.h;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列 实战（接收生产者的消息，将“消息被拒绝”、“消息TTL”、“队列达到最大长度”的消息转发到死信队列中）
 *
 * @author:厚积薄发
 * @create:2022-08-21-23:41
 */
public class Consumer01 {
    //普通交换机名称
    private static final String NORMAL_EXCHANGE = "normal_exchange";

    //死信交换机名称
    private static final String DEAD_EXCHANGE = "dead_exchange";

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //声明死信和普通交换机 类型为 direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);

        //声明死信队列
        String deadQueue = "dead-queue";
        channel.queueDeclare(deadQueue, false, false, false, null);

        //绑定 死信队列 与 死信交换机 routingkey
        channel.queueBind(deadQueue, DEAD_EXCHANGE, "lisi");

        /*-------------------------------------------------*/

        //正常队列绑定死信队列信息
        Map<String, Object> params = new HashMap<>();
        //正常队列设置死信交换机 参数 key 是固定值
        params.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //正常队列设置死信 routing-key 参数 key 是固定值
        params.put("x-dead-letter-routing-key", "lisi");
        //设置队列最大长度
        //params.put("x-max-length",6);

        //普通队列名称
        String normalQueue = "normal-queue";
        channel.queueDeclare(normalQueue, false, false, false, params);
        channel.queueBind(normalQueue, NORMAL_EXCHANGE, "zhangsan");
        System.out.println("等待接收消息.....");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            if(message.equals("info5")){
                System.out.println("Consumer01 接收到消息" + message + "并拒绝签收该消息");
                // 拒绝消息，requeue 设置为 false 代表拒绝重新入队 该队列如果配置了死信交换机将发送到死信队列中
                //channel.basicReject(delivery.getEnvelope().getDeliveryTag(), false);
            }else {
                System.out.println("Consumer01 接收到消息"+message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }        };

        channel.basicConsume(normalQueue, true/*false手动应答*/, deliverCallback, consumerTag -> {
        });
    }
}