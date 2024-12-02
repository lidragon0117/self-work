package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.service.ProcessService;
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
 * @date : 2024-12-02 22:04
 * @description : 流程管理抽象类
 */
@Slf4j
@Service("abstractProcessService")
public abstract class AbstractProcessService implements ProcessService {
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 开启流程
     *
     * @param processKey 模版Id
     * @return
     */
    @Override
    public abstract String start(String processKey);
    /**
     * 单个流程部署
     *
     * @return
     */
    @Override
    public Deployment deploy(String xmlPath,String processName) {
        File file = new File(xmlPath);
        try {
            return repositoryService.createDeployment()
                    .addInputStream(processName,new FileInputStream(file))
                    .deploy();
        } catch (FileNotFoundException e) {
            log.error("file not found exception :{}",e);
        }
        return null;
    }
}
