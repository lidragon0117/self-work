package com.lilong.workflow.core.controller;

import com.lilong.workflow.core.commons.request.DeployVo;
import com.lilong.workflow.core.service.DeploymentService;
import com.lilong.workflow.core.service.strategy.DeployStrategyFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lilong
 * @date : 2024-12-05 23:31
 * @description :
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    /**
     * 根据类型部署
     * @param deployVo
     */
    @PostMapping("/deployByType")
    public void deployByType(@RequestBody DeployVo deployVo){
        DeploymentService deploy = DeployStrategyFactory.getDeploy(deployVo.getDeployType());
        deploy.deployByType(deployVo);
    }
}
