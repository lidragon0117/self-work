package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.service.strategy.ProcessResourceService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : lilong
 * @date : 2024-12-09 22:58
 * @description : 流程资源抽象类
 */
public abstract class AbstractProcessResourceService implements ProcessResourceService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 单个流程实例挂起
     * @param processId
     * @return
     */
    @Override
    public Boolean suspendSingleProcess(String processId) {
        if(StringUtils.isEmpty(processId)){
            return false;
        }
        ProcessInstance processInstance = runtimeService.
                createProcessInstanceQuery().
                processInstanceId(processId).
                singleResult();
        boolean suspended = processInstance.isSuspended();
       if(suspended){
           runtimeService.activateProcessInstanceById(processInstance.getId());
       }else{
           runtimeService.suspendProcessInstanceById(processInstance.getId());
       }
        return true;
    }
    /**
     * 全部流程实例挂起
     * @param processKey
     * @return
     */
    @Override
    public Boolean suspendAllProcess(String processKey) {
        if(StringUtils.isEmpty(processKey)){
            return false;
        }
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processKey)
                .singleResult();
        if(processDefinition==null){
            return false;
        }
        boolean suspended = processDefinition.isSuspended();
        if(suspended){
            repositoryService.activateProcessDefinitionById(processDefinition.getId()
                    ,true,null);
        }else{
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,null);
        }
        return true;
    }
}
