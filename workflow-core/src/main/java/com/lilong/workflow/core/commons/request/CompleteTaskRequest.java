package com.lilong.workflow.core.commons.request;

import lombok.Data;

import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-07 22:39
 * @description :任务处理请求参数
 */
@Data
public class CompleteTaskRequest {
    /**
     * 流程Id
     */
    private String processId;
    /**
     * 流程key
     */
    private String processKey;
    /**
     * 任务负责人
     */
    private String assignee;
    /**
     * 操作字段
     */
    private Map<String, Object> variables;
}
