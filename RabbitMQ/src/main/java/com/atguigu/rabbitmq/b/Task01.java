package com.atguigu.rabbitmq.b;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * 生产者 可以发大量消息
 * @author:厚积薄发
 * @create:2022-08-17-10:39
 */
public class Task01 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    //发送消息
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //声明消息
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //从控制台中接收消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送消息完成：" + message);
        }
    }


}
