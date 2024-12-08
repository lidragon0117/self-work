package com.lilong.workflow.core.commons.request;

import lombok.Data;

/**
 * @author : lilong
 * @date : 2024-12-08 20:47
 * @description : 获取任务流程
 */
@Data
public class ProcessTaskRequest {
    /**
     * 流程实例Id
     */
    private String processId;
    /**
     * 流程定义key
     */
    private String processKey;
    /**
     * 当前处理人
     */
    private String currentUser;
}
