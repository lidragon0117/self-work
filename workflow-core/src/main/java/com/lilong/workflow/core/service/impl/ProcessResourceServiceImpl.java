package com.lilong.workflow.core.service.impl;

import com.lilong.workflow.core.service.base.AbstractProcessResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : lilong
 * @date : 2024-12-09 22:58
 * @description : 流程资源实现类
 */
@Slf4j
@Service
public class ProcessResourceServiceImpl extends AbstractProcessResourceService {

    /**
     * 单个流程实例挂起
     * @param processId
     * @return
     */
    @Override
    public Boolean suspendSingleProcess(String processId) {
        return super.suspendSingleProcess(processId);
    }

    /**
     * 挂起所有流程实例
     * @param processKey
     * @return
     */
    @Override
    public Boolean suspendAllProcess(String processKey) {
        return super.suspendAllProcess(processKey);
    }


}
