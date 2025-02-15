package com.lilong.massage.core.base.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : lilong
 * @date : 2024-12-29 19:40
 * @description :
 */
@Slf4j
public class WorkQueueRabbitConsumer extends DefaultConsumer {

    /**
     * 创建
     * @param channel
     */
    public WorkQueueRabbitConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)  {
        try{
            super.handleDelivery(consumerTag, envelope, properties, body);
        }catch (Exception e){
            log.error("handleDelivery is error",e);
        }
    }
}
