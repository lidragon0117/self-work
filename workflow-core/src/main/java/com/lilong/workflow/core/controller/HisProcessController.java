package com.lilong.workflow.core.controller;

import com.lilong.workflow.core.commons.request.HisProcessRequest;
import com.lilong.workflow.core.commons.response.base.BaseResponse;
import com.lilong.workflow.core.service.HistoricActivityService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-08 22:13
 * @description : 历史审批流程
 */
@RestController
@RequestMapping("/hisProcess")
public class HisProcessController {
    @Autowired
    private HistoricActivityService historicActivityService;

    /**
     * 获取历史流程信息
     * @param hisProcessRequest
     * @return
     */
    @PostMapping("/handleProcess")
    public BaseResponse<List<HistoricActivityInstance>> handleProcess(@RequestBody HisProcessRequest hisProcessRequest){
        return BaseResponse.success(historicActivityService.getHisProcessByProcessId(hisProcessRequest));
    }

    /**
     * 获取历史审批数据
     * @param hisProcessRequest
     * @return
     */
    @PostMapping("/hisVariables")
    public BaseResponse<List<HistoricVariableInstance>> hisVariables(@RequestBody HisProcessRequest hisProcessRequest){
        return BaseResponse.success(historicActivityService.getHisVariables(hisProcessRequest));
    }

}
