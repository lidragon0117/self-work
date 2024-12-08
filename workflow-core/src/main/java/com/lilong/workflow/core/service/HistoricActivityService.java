package com.lilong.workflow.core.service;

import com.lilong.workflow.core.commons.request.HisProcessRequest;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-07 20:11
 * @description : 流程历史信息
 */
public interface HistoricActivityService {
    /**
     * 根据流程id 获取历史流程信息
     * @param hisProcessRequest
     * @return
     */
    List<HistoricActivityInstance> getHisProcessByProcessId(HisProcessRequest hisProcessRequest);

    /**
     * 获取所有审批操作数据
     * @param hisProcessRequest
     * @return
     */
    List<HistoricVariableInstance> getHisVariables(HisProcessRequest hisProcessRequest);
}
