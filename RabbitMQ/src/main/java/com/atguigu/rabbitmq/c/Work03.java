package com.atguigu.rabbitmq.c;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.atguigu.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消息在手动应答时是不丢失、放回队列中重新消费(手动应答)
 * @author:厚积薄发
 * @create:2022-08-17-13:13
 */
public class Work03 {

    //队列名称
    public  static final String TASK_QUEUE_NAME = "ack_queue";

    //接收消息
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("C1..等待接收消息处理时间较短");

        //消息的接收
        DeliverCallback deliverCallback=(consumerTag, message)->{
            //沉睡1s
            SleepUtils.sleep(1);
            System.out.println("接收到消息:"+new String(message.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1.消息的标记 tag
             * 2.是否批量量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        //消息取消消费
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };

        //0：默认轮训分发、1：不公平分发、其他：预取值
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        //采用手动应答
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,cancelCallback);
    }


}
