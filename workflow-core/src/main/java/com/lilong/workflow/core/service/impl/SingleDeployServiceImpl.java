package com.lilong.workflow.core.service.impl;

import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.base.AbstractDeployService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;

/**
 * @author : lilong
 * @date : 2024-12-05 22:10
 * @description : 单个文件方式部署
 */
@Slf4j
@Service("singleDeploy")
public class SingleDeployServiceImpl extends AbstractDeployService {
    /**
     * 单个文件方式部署
     * @param deployVo
     * @return
     */
    @Override
    public Deployment deployByType(DeployVo deployVo) {
        return super.deploy(deployVo);
    }
}
