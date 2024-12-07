package com.lilong.workflow.core.service;

import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-07 19:13
 * @description : 流程任务
 */
public interface ProcessTaskService {
    /**
     * 获取当前审批流程
     * @param processId
     * @param assignee
     * @return
     */
    Task getCurrentTask(String processId, String assignee);

    /**
     * 获取所有流程
     * @param processId
     * @param assignee
     * @return
     */
    List<Task> getProcessTaskList(String processId, String assignee);

    /**
     * 操作审批当前节点
     * @param taskId
     * @param varLogs
     * @return
     */
    Boolean completeTask(String taskId, Map<String,Object> varLogs);
}
