package com.atguigu.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**此类为连接工厂创建信道的工具类
 * @author:厚积薄发
 * @create:2022-08-17-10:19
 */
public class RabbitMqUtils {

    //得到一个连接channel
    public static Channel getChannel() throws Exception{
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP 连接RabbitMQ的队列
        factory.setHost("192.168.72.128");
        //用户名
        factory.setUsername("root");
        //密码
        factory.setPassword("abc123");
        //创建连接
        Connection connection = factory.newConnection();
        //获取连接里面的信道
        Channel channel = connection.createChannel();
        return channel;
    }

}
