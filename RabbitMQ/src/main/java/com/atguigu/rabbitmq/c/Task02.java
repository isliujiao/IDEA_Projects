package com.atguigu.rabbitmq.c;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * 消息在手动应答时是不丢失、放回队列中重新消费
 * @author:厚积薄发
 * @create:2022-08-17-13:08
 */
public class Task02 {

    //队列名称
    public  static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //开启发布确认
        channel.confirmSelect();

        //声明队列
        //让消息队列持久化
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            //消息持久化 "MessageProperties.PERSISTENT_TEXT_PLAIN"
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("发送消息完成：" + message);
        }
    }

}
