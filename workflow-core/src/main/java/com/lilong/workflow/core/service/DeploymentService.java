package com.lilong.workflow.core.service;

import com.lilong.workflow.core.commons.request.DeployVo;
import org.activiti.engine.repository.Deployment;

/**
 * @author : lilong
 * @date : 2024-12-05 22:08
 * @description : 资源部署接口
 */
public interface DeploymentService {
    /**
     * 根据类型进行资源部署
     * @param deployVo
     * @return
     */
    Deployment deployByType(DeployVo deployVo);
}
