package com.lilong.workflow.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.base.AbstractDeployService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author : lilong
 * @date : 2024-12-05 22:10
 * @description : 单个文件方式部署
 */
@Slf4j
@Service("singleDeploy")
public class SingleDeployServiceImpl extends AbstractDeployService {
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 单个文件方式部署
     * @param deployVo
     * @return
     */
    @Override
    public Deployment deployByType(DeployVo deployVo) {
        File file = new File(deployVo.getResourcePath());
        try {
            InputStream inputStream=new FileInputStream(file);
           return repositoryService.createDeployment()
                    .name(deployVo.getResourceName())
                    .key(deployVo.getDeployKey())
                    .tenantId(deployVo.getTenantId())
                    .addInputStream(deployVo.getResourceName(),inputStream)
                    .deploy();
        } catch (FileNotFoundException e) {
            log.error("file not found ,params{}", JSONObject.toJSON(deployVo));
        }
        return super.defaultDeploy(deployVo);
    }
}
