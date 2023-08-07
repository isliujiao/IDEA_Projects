package com.atguigu.rabbitmq.b;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消息在手动应答时是不丢失、放回队列中重新消费
 * @author:厚积薄发
 * @create:2022-08-17-10:22
 */
public class Worker01 {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //得到连接信道
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String receivedMessage = new String(delivery.getBody());
            System.out.println("接收到消息:"+receivedMessage);
        };

        //消息取消消费
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };

        System.out.println("C2 消费者启动等待消费......");

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }

}
