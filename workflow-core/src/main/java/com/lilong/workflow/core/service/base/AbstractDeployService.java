package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.DeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author : lilong
 * @date : 2024-12-05 22:07
 * @description : 资源部署抽象类
 */

@Slf4j
public abstract class AbstractDeployService implements DeploymentService {
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 默认单个文件部署
     * @param deployVo
     * @return
     */
    protected Deployment defaultDeploy(DeployVo deployVo) {
        File file = new File(deployVo.getResourcePath());
        try {
            InputStream inputStream=new FileInputStream(file);
           return repositoryService.createDeployment()
                    .name(deployVo.getResourceName())
                    .addInputStream(deployVo.getResourceName(),inputStream)
                    .deploy();
        } catch (FileNotFoundException e) {
            log.error("File not found{}",e);
        }
        return null;
    }

    /**
     * 根据类型选择部署方式
     * @param deployVo
     * @return
     */
    @Override
    public abstract Deployment deployByType(DeployVo deployVo);

    /**
     * 获取流程定义信息
     * @param processKey
     * @return
     */
    @Override
    public ProcessDefinition getProcessDefinition(String processKey) {
        if(StringUtils.isEmpty(processKey)){
            log.info("processDefinition processKey is empty!");
            return null;
        }
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        return processDefinitionQuery
                .processDefinitionKey(processKey)
                .desc()
                .latestVersion().singleResult();
    }
}
