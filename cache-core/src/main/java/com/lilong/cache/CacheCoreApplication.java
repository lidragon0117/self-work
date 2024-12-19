package com.lilong.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通用缓存模块
 * @author lilong
 */
@Slf4j
@SpringBootApplication
public class CacheCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheCoreApplication.class, args);
        log.info("Cache Core Application started!!!");
    }

}
