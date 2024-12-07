package com.lilong.workflow.core.service;

import org.activiti.engine.history.HistoricActivityInstance;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-07 20:11
 * @description : 流程历史信息
 */
public interface HistoricActivityService {
    /**
     * 根据流程id 获取历史流程信息
     * @param processId
     * @return
     */
    List<HistoricActivityInstance> getHisProcessByProcessId(String processId);
}
