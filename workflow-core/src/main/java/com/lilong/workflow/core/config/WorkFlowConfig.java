package com.lilong.workflow.core.config;

import org.activiti.engine.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * @author : lilong
 * @date : 2024-12-02 21:56
 * @description :
 */
@SpringBootConfiguration
public class WorkFlowConfig {
    /**
     * 获取引擎

     * @return
     */
    @Bean
    public ProcessEngine getProcessEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * 流程运行管理类
     * @return
     */
    @Bean
    public RuntimeService getRuntimeService(){
        return getProcessEngine().getRuntimeService();
    }

    /**
     * 流程资源管理
     * @return
     */
    @Bean
    public RepositoryService getRepositoryService(){
        return getProcessEngine().getRepositoryService();
    }

    /**
     * 流程任务管理
     * @return
     */
    @Bean
    public TaskService getTaskService(){
        return getProcessEngine().getTaskService();
    }

    /**
     * 历史管理
     * @return
     */
    @Bean
    public HistoryService getHistoryService(){
        return getProcessEngine().getHistoryService();
    }
}
