package com.lilong.massage.core.utils;

import com.lilong.massage.core.config.RabbitMQConfig;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : lilong
 * @date : 2024-12-29 16:11
 * @description : rabbitMQ 工具类
 */
//@Component
@Slf4j
public class RabbitMQUtils {
    @Autowired
    private static RabbitMQConfig rabbitMQConfig;

    /**
     * 构建连接
     * @return
     */
    public static Connection getConnection(){
       try{
           ConnectionFactory factory = new ConnectionFactory();
           factory.setPort(rabbitMQConfig.getPort());
           factory.setHost(rabbitMQConfig.getHost());
           factory.setVirtualHost(rabbitMQConfig.getVirtualHost());
           factory.setPassword(rabbitMQConfig.getPassword());
           factory.setUsername(rabbitMQConfig.getUserName());
           return factory.newConnection();
       }catch (Exception e){
           log.error("getConnection is error",e);
       }
       return null;
    }
}
