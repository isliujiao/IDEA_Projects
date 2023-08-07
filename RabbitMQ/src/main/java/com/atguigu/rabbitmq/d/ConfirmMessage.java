package com.atguigu.rabbitmq.d;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author:厚积薄发
 * @create:2022-08-18-11:10 发布确认模式
 * 使用的时间 比较哪个确认方式是最好的
 * 1、单个确认
 * 2、批量确认
 * 3、异步批量确认
 */
public class ConfirmMessage {

    //批量消息个数
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        //1、单个确认
//        ConfirmMessage.Individually();//发布1000个单独确认消息，耗时：530ms
        //2、批量确认
//        ConfirmMessage.Batch();//发布1000个批量确认消息，耗时：65ms
        //3、异步批量确认
        ConfirmMessage.Async();//发布1000个异步确认消息，耗时：49ms
                                //发布1000个异步确认消息，耗时：37ms
    }

    //1、单个确认
    public static void Individually() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin = System.currentTimeMillis();

        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            //单个消息马上进行发布确认
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功");
            }
        }

        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息，耗时：" + (end - begin) + "ms");
    }

    //2、批量确认
    public static void Batch() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin = System.currentTimeMillis();

        //批量发送消息 批量发布确认
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            if(i % 100 == 0){
                //发布确认
                channel.waitForConfirms();
            }
        }

        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息，耗时：" + (end - begin) + "ms");

    }

    //3、异步批量确认
    public static void Async() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin = System.currentTimeMillis();

        /**
         * 线程安全有序一个哈希表 适用于高并发的情况下
         * 1.将序号与消息进行关联
         * 2.批量删除条目
         * 3.支持高并发
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();

        //消息确认成功 回调函数
        ConfirmCallback ackCallback = (deliveryTag,mulitiple) -> {
            //TODO 2.删除掉已经确认的消息 剩下的就是未确认的消息
            if(mulitiple){
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息：" + deliveryTag);
        };
        //消息确认失败 回调函数
        ConfirmCallback nackCallback = (deliveryTag,mulitiple) -> {
            //TODO 3.未确认的消息
            System.out.println("未确认的消息：" + deliveryTag + ", 未确认的消息tag：" + deliveryTag);
        };
        //准备消息的监听器，监听哪些消息成功，哪些失败
        channel.addConfirmListener(ackCallback,nackCallback);

        //批量发送消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            //TODO 1.记录所有要发送的消息 消息总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }

        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息，耗时：" + (end - begin) + "ms");
    }
}
