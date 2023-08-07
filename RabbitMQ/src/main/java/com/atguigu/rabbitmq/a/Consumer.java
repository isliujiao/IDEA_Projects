package com.atguigu.rabbitmq.a;

import com.rabbitmq.client.*;

/**
 * @author:厚积薄发
 * @create:2022-08-16-23:19
 * 消费者  接收消息
 */
public class Consumer {
    //队列的名称
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.72.128");
        factory.setUsername("root");
        factory.setPassword("abc123");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //声明 接收消息
        DeliverCallback deliverCallback = (consumerTag,delivery) -> {
            String message= new String(delivery.getBody());
            System.out.println(message);
        };
        //声明 取消接收消息
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println("消息消费被中断！");
        };

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否自动应答 true代表自动应答 false代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }

}
