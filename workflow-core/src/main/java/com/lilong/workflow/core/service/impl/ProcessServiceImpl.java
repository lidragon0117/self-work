package com.lilong.workflow.core.service.impl;


import com.lilong.workflow.core.commons.request.ProcessStartRequest;
import com.lilong.workflow.core.service.ProcessService;
import com.lilong.workflow.core.service.base.AbstractProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RepositoryService repositoryService;

    /**
     * 发起流程
     * @param startRequest
     * @return
     */
    @Override
    public String start(ProcessStartRequest startRequest) {

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery
                .processDefinitionKey(startRequest.getProcessKey())
                .latestVersion().singleResult();

        if (processDefinition == null) {
            log.error("ProcessDefinition is not found");
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startRequest.getProcessKey(), startRequest.getBusinessKey(), startRequest.getVariables());
        return processInstance.getId();
    }
}
