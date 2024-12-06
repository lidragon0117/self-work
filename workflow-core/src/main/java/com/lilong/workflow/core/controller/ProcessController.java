package com.lilong.workflow.core.controller;


import com.lilong.workflow.core.commons.request.ProcessStartRequest;
import com.lilong.workflow.core.commons.response.base.BaseResponse;
import com.lilong.workflow.core.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lilong
 * @date : 2024-12-02 22:07
 * @description : 流程管理
 */
@RestController
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 发起流程
     *
     * @param processStartRequest
     * @return
     */
    @PostMapping("/start")
    public BaseResponse processStart(@RequestBody ProcessStartRequest processStartRequest) {
        return BaseResponse.success(processService.start(processStartRequest));
    }


}
