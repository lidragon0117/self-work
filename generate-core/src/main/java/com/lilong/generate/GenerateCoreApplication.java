package com.lilong.generate;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分布式ID生成策略
 * @author lilong
 */
@SpringBootApplication
@Slf4j
public class GenerateCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateCoreApplication.class, args);
        System.out.println("GenerateCoreApplication started");
    }

}
