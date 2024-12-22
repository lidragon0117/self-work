package com.lilong.workflow.core.service;

import com.lilong.workflow.core.commons.request.ProcessTaskRequest;
import com.lilong.workflow.core.commons.response.CurrentTaskVO;
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
     * 获取当前任务
     * @param processTask
     * @return
     */
    Task getCurrentTask(ProcessTaskRequest processTask);

    /**
     * 获取所有流程(用户组)
     * @param processTaskRequest
     * @return
     */
    List<CurrentTaskVO> getProcessTaskGroupList(ProcessTaskRequest processTaskRequest);

    /**
     * 操作审批当前节点
     * @param taskId
     * @param varLogs
     * @return
     */
    Boolean completeTask(String taskId, Map<String,Object> varLogs);

    /**
     * 拾取任务
     * @param processTaskRequest
     * @return
     */
    Boolean claimTask(ProcessTaskRequest processTaskRequest);

    /**
     *  归还任务/转办任务
     * @param processTaskRequest
     * @return
     */
    Boolean updateAssigneeTask(ProcessTaskRequest processTaskRequest);

    /**
     * 获取当前用户的所有待审批任务
     * @param processTaskRequest
     * @return
     */
    List<CurrentTaskVO> currentTaskList(ProcessTaskRequest processTaskRequest);
}
