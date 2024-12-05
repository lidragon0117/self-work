package com.lilong.workflow.core.service;


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
}
