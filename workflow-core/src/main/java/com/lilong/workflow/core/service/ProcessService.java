package com.lilong.workflow.core.service;


import com.lilong.workflow.core.commons.request.ProcessStartRequest;

/**
 * @author : lilong
 * @date : 2024-12-02 22:00
 * @description : 流程管理接口
 */
public interface ProcessService {
    /**
     * 开启流程
      * @return
     * @param startRequest
     */
    String start(ProcessStartRequest startRequest);
}
