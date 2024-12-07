package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.service.ProcessTaskService;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-07 19:13
 * @description :
 */
@Slf4j
public abstract class AbstractTaskService implements ProcessTaskService {
    /**
     * 获取当前流程任务
     * @param processId
     * @param assignee
     * @return
     */
    @Override
    public abstract Task getCurrentTask(String processId, String assignee);

    /**
     * 获取所有任务
     * @param processId
     * @param assignee
     * @return
     */
    @Override
    public abstract List<Task> getProcessTaskList(String processId, String assignee);

    /**
     * 操作审批当前节点
     * @param taskId
     * @param varLogs
     * @return
     */
    @Override
    public abstract Boolean completeTask(String taskId, Map<String, Object> varLogs);
}
