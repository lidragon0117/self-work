package com.lilong.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : lilong
 * @date : 2024-12-01 19:19
 * @description :
 */
@SpringBootApplication
@Slf4j
public class WorkFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApplication.class, args);
        log.info("Workflow application started");
    }
}
