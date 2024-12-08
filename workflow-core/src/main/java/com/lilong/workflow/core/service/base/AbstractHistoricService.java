package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.commons.request.HisProcessRequest;
import com.lilong.workflow.core.service.HistoricActivityService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-07 20:11
 * @description : 流程历史记录
 */
@Slf4j
public abstract class AbstractHistoricService implements HistoricActivityService {
    @Autowired
    private HistoryService historyService;

    /**
     * 获取历史流程记录
     * @param hisProcessRequest
     * @return
     */
    @Override
    public List<HistoricActivityInstance> getHisProcessByProcessId(HisProcessRequest hisProcessRequest) {
        return historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(hisProcessRequest.getProcessId())
                .list();
    }

    /**
     * 获取所有审批数据
     * @param hisProcessRequest
     * @return
     */
    @Override
    public List<HistoricVariableInstance> getHisVariables(HisProcessRequest hisProcessRequest) {
        return historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(hisProcessRequest.getProcessId())
                .list();
    }
}
