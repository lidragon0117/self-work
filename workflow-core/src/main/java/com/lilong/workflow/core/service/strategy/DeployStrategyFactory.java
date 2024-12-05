package com.lilong.workflow.core.service.strategy;

import com.lilong.workflow.core.service.DeploymentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-05 22:25
 * @description : 部署策略模式
 */
@Service
public class DeployStrategyFactory {

    @Autowired
    private  ApplicationContext context;
    /**
     * 存储
     */
    private static Map<String, DeploymentService> deployMap;

    /**
     * 初始化发布bean到map存储用于策略模式
     */
    @PostConstruct
    public void init(){
        deployMap = context.getBeansOfType(DeploymentService.class);
    }
    /**
     *  获取部署方式
     * @param deployType 部署方式
     * @return
     */
    public static DeploymentService getDeploy(String deployType){
        return deployMap.get(deployType);
    }
}
