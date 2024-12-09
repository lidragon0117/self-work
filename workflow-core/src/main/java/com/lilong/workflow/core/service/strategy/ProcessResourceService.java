package com.lilong.workflow.core.service.strategy;

/**
 * @author : lilong
 * @date : 2024-12-09 22:56
 * @description : 流程资源接口
 */
public interface ProcessResourceService {
    /**
     * 挂起所有流程实例
     * @param processKey
     * @return
     */
    Boolean suspendAllProcess(String processKey);

    /**
     * 单个实例流程挂起
     * @param processId
     * @return
     */
    Boolean suspendSingleProcess(String processId);
}
