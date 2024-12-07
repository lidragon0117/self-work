package com.lilong.workflow.core.service.base;

import com.lilong.workflow.core.service.HistoricActivityService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param processId
     * @return
     */
    @Override
    public List<HistoricActivityInstance> getHisProcessByProcessId(String processId) {
        return historyService.createHistoricActivityInstanceQuery()
                .activityInstanceId(processId)
                .desc()
                .list();
    }
}
