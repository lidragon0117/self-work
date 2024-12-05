package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.DeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;

/**
 * @author : lilong
 * @date : 2024-12-05 22:07
 * @description : 资源部署抽象类
 */
@Service
@Slf4j
public abstract class AbstractDeployService implements DeploymentService {
    /**
     * 部署详情
     * @param deployVo
     * @return
     */
    @Override
    public Deployment deploy(DeployVo deployVo) {
        return null;
    }

    /**
     * 根据类型选择部署方式
     * @param deployVo
     * @return
     */
    public abstract Deployment deployByType(DeployVo deployVo);
}
