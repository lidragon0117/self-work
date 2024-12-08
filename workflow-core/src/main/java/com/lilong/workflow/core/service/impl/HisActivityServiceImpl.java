package com.lilong.workflow.core.service.impl;

import com.lilong.workflow.core.commons.request.HisProcessRequest;
import com.lilong.workflow.core.service.base.AbstractHistoricService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-08 22:15
 * @description : 历史流程实现类
 */
@Slf4j
@Service
public class HisActivityServiceImpl extends AbstractHistoricService {
    /**
     * 历史审批流程查询
     * @param hisProcessRequest 查询历史流程参数
     * @return
     */
    @Override
    public List<HistoricActivityInstance> getHisProcessByProcessId(HisProcessRequest hisProcessRequest) {
        return super.getHisProcessByProcessId(hisProcessRequest);
    }

    /**
     * 获取所有审批操作数据
     * @param hisProcessRequest
     * @return
     */
    @Override
    public List<HistoricVariableInstance> getHisVariables(HisProcessRequest hisProcessRequest) {
        return super.getHisVariables(hisProcessRequest);
    }
}
