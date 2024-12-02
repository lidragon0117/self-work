package com.lilong.workflow.core.controller;

import com.lilong.workflow.core.commons.request.SingleDeployVo;
import com.lilong.workflow.core.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lilong
 * @date : 2024-12-02 22:07
 * @description : 流程管理
 */
@RequestMapping("/process")
@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 单个流程部署
     */
    @GetMapping("/deploy")
    public void deploy(@RequestBody SingleDeployVo deployVo) {
        processService.deploy(deployVo.getXmlPath(), deployVo.getXmlName());
    }

}
