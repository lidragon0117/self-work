package com.lilong.massage.core.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : lilong
 * @date : 2024-12-29 16:15
 * @description : RabbitMQ 配置文件
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitMQConfig {
    /**
     * 端口号
     */
    private int port;
    /**
     * ip地址
     */
    private String host;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 主机
     */
    private String virtualHost;

}
