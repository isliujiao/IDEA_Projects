package com.atguigu.rabbitmq.a;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author:厚积薄发
 * @create:2022-08-16-23:01
 * 生产者：发送消息
 */
public class Producer {

    //队列名称
    public static final  String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args) throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.72.128");//工厂IP 连接RabbitMQ的队列
        factory.setUsername("root");//用户名
        factory.setPassword("abc123");//密码
        //创建连接
        Connection connection = factory.newConnection();
        //获取连接里面的信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列：
         * 1.队列名称
         * 2.队列里面的消息是否持久化（磁盘） 默认情况消息存储在内存中（false）
         * 3.该队列是否只供一个消费者进行消费 是否进行消息共享，true可以多个消费者消费 false 一个消费者消费
         * 4.是否自动删除 最后一个消费者断开连接以后 该队列是否自动删除 true自动删除 false 不自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发消息
        String message = "hello world";
        /**
         * 发送一个消息
         * 1.发送到哪个交换机
         * 2.路由的Key值是哪个，本次是队列的名称
         * 3.其他参数消息
         * 4.发送消息的消息体
         */
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("",QUEUE_NAME,null,(message + i).getBytes());
            System.out.println("消息发送完毕" + i);
        }
    }
}
