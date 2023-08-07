package com.atguigu.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 回调接口
 *
 * @author:厚积薄发
 * @create:2022-08-23-23:59
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    /**
     * 交换机确认回调方法
     * 发消息 交换机接收 回调
     * 1.correlationData 保存回调消息的id以及相关信息
     * 2.交换机收到信息 ack = true(接收成功)、false(接收失败)
     * 3.cause = null(接收成功)、失败原因(接受失败)
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = (correlationData != null) ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到 id 为:{}的消息", id);
        } else {
            log.info("交换机还未收到 id 为:{}消息,由于原因:{}", id, cause);
        }
    }

    /**
     * 交换机回退接口（在当消息传递过程中不可达目的地时将消息返回给生产者）
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息{},被交换机{}退回，退回原因:{},路由key:{}", new String(message.getBody()), exchange, replyText, routingKey);
    }


}
