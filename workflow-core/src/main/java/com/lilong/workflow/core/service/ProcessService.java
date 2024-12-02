package com.lilong.workflow.core.service;

import org.activiti.engine.repository.Deployment;

/**
 * @author : lilong
 * @date : 2024-12-02 22:00
 * @description : 流程管理接口
 */
public interface ProcessService {
    /**
     * 开启流程
      * @return
     * @param processKey
     */
    String start(String processKey);

    /**
     * 单个流程定义发布
     * @param xmlPath 流程路径地址
     * @param processName 流程名字
     * @return
     */
    Deployment deploy(String xmlPath,String processName);
}
