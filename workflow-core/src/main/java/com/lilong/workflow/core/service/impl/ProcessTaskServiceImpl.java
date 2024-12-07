package com.lilong.workflow.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.lilong.workflow.core.commons.response.base.BaseException;
import com.lilong.workflow.core.service.base.AbstractTaskService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-07 19:14
 * @description : 流程任务实现类
 */
@Service
public class ProcessTaskServiceImpl extends AbstractTaskService {
    @Autowired
    private TaskService taskService;

    /**
     * 获取当前流程任务节点
     *
     * @param processId 流程Id
     * @param assignee  审批人
     * @return
     */
    @Override
    public Task getCurrentTask(String processId, String assignee) {
        if (StringUtils.isEmpty(processId)) {
            throw new BaseException("processId cannot be empty!");
        }
        if (StringUtils.isEmpty(assignee)) {
            return taskService.createTaskQuery().processInstanceId(processId).singleResult();
        }
        return taskService.createTaskQuery()
                .processInstanceId(processId)
                .taskAssignee(assignee)
                .singleResult();
    }

    /**
     * 获取所有流程任务
     *
     * @param processId
     * @param assignee
     * @return
     */
    @Override
    public List<Task> getProcessTaskList(String processId, String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .processInstanceId(processId)
                .desc().
                list();
    }

    /**
     * 任务审批
     * @param taskId
     * @param varLogs
     * @return
     */
    @Override
    public Boolean completeTask(String taskId, Map<String, Object> varLogs) {
        taskService.complete(taskId, varLogs);
        return true;
    }
}
