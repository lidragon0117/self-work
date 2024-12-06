package com.lilong.workflow.core.service.impl;

import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.base.AbstractDeployService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author : lilong
 * @date : 2024-12-05 22:10
 * @description : zip文件包部署方式
 */
@Slf4j
@Service("zipDeploy")
public class ZipDeployServiceImpl extends AbstractDeployService {

    @Autowired
    private RepositoryService repositoryService;
    /**
     * zip 压缩包的形式部署
     *
     * @param deployVo
     * @return
     */
    @Override
    public Deployment deployByType(DeployVo deployVo) {
        InputStream inputStream=this.getClass().getResourceAsStream(deployVo.getResourceName());
        if(inputStream==null){
            log.error("zip not found{}");
        }
        ZipInputStream zipInputStream=new ZipInputStream(inputStream);
        return repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
    }
}
