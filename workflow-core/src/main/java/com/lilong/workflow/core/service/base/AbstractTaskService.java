package com.lilong.workflow.core.service.base;

import cn.hutool.core.collection.CollUtil;
import com.lilong.workflow.core.commons.enums.OperateTypeEnums;
import com.lilong.workflow.core.commons.request.ProcessTaskRequest;
import com.lilong.workflow.core.commons.response.CurrentTaskVO;
import com.lilong.workflow.core.commons.response.base.BaseException;
import com.lilong.workflow.core.service.ProcessTaskService;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : lilong
 * @date : 2024-12-07 19:13
 * @description :
 */
@Slf4j
public abstract class AbstractTaskService implements ProcessTaskService {
    @Autowired
    private TaskService taskService;

    /**
     * 获取当前流程任务
     *
     * @param processId
     * @param assignee
     * @return
     */
    @Override
    public abstract Task getCurrentTask(String processId, String assignee);

    /**
     * 获取当前任务
     *
     * @param processTask
     * @return
     */
    @Override
    public abstract Task getCurrentTask(ProcessTaskRequest processTask);

    /**
     * 获取所有任务
     *
     * @param processTaskRequest
     * @return
     */
    @Override
    public abstract List<CurrentTaskVO> getProcessTaskGroupList(ProcessTaskRequest processTaskRequest);

    /**
     * 操作审批当前节点
     *
     * @param taskId
     * @param varLogs
     * @return
     */
    @Override
    public abstract Boolean completeTask(String taskId, Map<String, Object> varLogs);

    /**
     * 拾取任务
     *
     * @param processTaskRequest
     * @return
     */
    @Override
    public Boolean claimTask(ProcessTaskRequest processTaskRequest) {
        Task task = taskService.createTaskQuery()
                .taskId(processTaskRequest.getTaskId())
                .taskCandidateUser(processTaskRequest.getCurrentUser())
                .singleResult();
        if (task == null) {
            throw new BaseException("claim task failed not found task");
        }
        taskService.claim(task.getId(), processTaskRequest.getCurrentUser());
        return true;
    }

    /**
     * 归还任务/转办任务
     *
     * @param processTaskRequest
     * @return
     */
    @Override
    public Boolean updateAssigneeTask(ProcessTaskRequest processTaskRequest) {
        Task task = taskService.createTaskQuery()
                .taskId(processTaskRequest.getTaskId())
                .taskAssignee(processTaskRequest.getCurrentUser())
                .singleResult();
        if (task == null) {
            throw new BaseException("claim task failed not found task");
        }
        // 归还任务组
        taskService.setAssignee(task.getId(), processTaskRequest.getNextHandleUser());
        return true;
    }

    /**
     * 获取当前用户需要执行的任务
     *
     * @param processTaskRequest
     * @return
     */
    @Override
    public List<CurrentTaskVO> currentTaskList(ProcessTaskRequest processTaskRequest) {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(processTaskRequest.getCurrentUser())
                .processInstanceId(processTaskRequest.getProcessId())
                .includeProcessVariables()
                .list();
        if (CollUtil.isEmpty(list)) {
            return Lists.emptyList();
        }
        return list.stream().map(x -> CurrentTaskVO.builder()
                .name(x.getName())
                .taskId(x.getId())
                .assignee(x.getAssignee())
                .description(x.getDescription())
                .processId(x.getProcessInstanceId())
                .processVariables(x.getProcessVariables())
                .taskVariables(x.getTaskLocalVariables())
                .build()).collect(Collectors.toList());
    }
}
