package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.commons.request.ProcessStartRequest;
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
public abstract class AbstractProcessService implements ProcessService {
    /**
     * 开启流程
     *
     * @param processStart
     * @return
     */
    @Override
    public abstract String start(ProcessStartRequest processStart);
}
