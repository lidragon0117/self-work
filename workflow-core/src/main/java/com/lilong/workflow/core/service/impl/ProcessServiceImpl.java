package com.lilong.workflow.core.service.impl;


import com.lilong.workflow.core.commons.request.CompleteTaskRequest;
import com.lilong.workflow.core.commons.request.ProcessStartRequest;
import com.lilong.workflow.core.commons.response.base.BaseException;
import com.lilong.workflow.core.service.DeploymentService;
import com.lilong.workflow.core.service.ProcessTaskService;
import com.lilong.workflow.core.service.base.AbstractProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author : lilong
 * @date : 2024-12-02 22:01
 * @description : 流程管理实现类
 */
@Slf4j
@Service
public class ProcessServiceImpl extends AbstractProcessService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    @Qualifier("singleDeploy")
    private DeploymentService deploymentService;
    @Autowired
    private ProcessTaskService processTaskService;
    /**
     * 发起流程
     * @param startRequest
     * @return
     */
    @Override
    public String start(ProcessStartRequest startRequest) {
        ProcessDefinition processDefinition = deploymentService.getProcessDefinition(startRequest.getProcessKey());
        if (processDefinition == null) {
            log.error("ProcessDefinition is not found");
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                startRequest.getProcessKey(),
                startRequest.getBusinessKey(),
                startRequest.getVariables()
        );
        return processInstance.getId();
    }

    /**
     * 处理当前任务
     * @param completeTask
     * @return
     */
    @Override
    public Boolean completeTask(CompleteTaskRequest completeTask) {
        Task currentTask = processTaskService.getCurrentTask(completeTask.getProcessId(), completeTask.getAssignee());
        if(currentTask==null){
            throw  new BaseException("task does not exist");
        }
        return processTaskService.completeTask(currentTask.getId(),completeTask.getVariables());
    }
}
