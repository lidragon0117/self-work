package com.lilong.workflow.core.controller;

import com.lilong.workflow.core.commons.request.ProcessTaskRequest;
import com.lilong.workflow.core.commons.response.base.BaseException;
import com.lilong.workflow.core.commons.response.base.BaseResponse;
import com.lilong.workflow.core.commons.response.CurrentTaskVO;
import com.lilong.workflow.core.service.ProcessTaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : lilong
 * @date : 2024-12-08 20:43
 * @description : 流程任务
 */
@RequestMapping("/processTask")
@RestController
public class ProcessTaskController {
    @Autowired
    private ProcessTaskService processTaskService;

    /**
     * 获取当前用户流程
     *
     * @param processTaskRequest
     * @return
     */
    @PostMapping("/currentTask")
    public BaseResponse getCurrentTask(@RequestBody ProcessTaskRequest processTaskRequest) {
        Task currentTask = processTaskService.getCurrentTask(processTaskRequest);
        if(currentTask==null){
            throw new BaseException("task not found");
        }
        CurrentTaskVO build = CurrentTaskVO.builder()
                .priority(currentTask.getPriority())
                .assignee(currentTask.getAssignee())
                .isSuspended(currentTask.isSuspended())
                .description(currentTask.getDescription())
                .name(currentTask.getName())
                .build();
        return BaseResponse.success(build);
    }

    /**
     * 获取当前用户流程下所有任务
     * @param processTaskRequest
     * @return all Task
     */
    @PostMapping("/belongToCurrentUser")
    public BaseResponse<List<CurrentTaskVO>> belongsToCurrentUser(@RequestBody ProcessTaskRequest processTaskRequest) {
        return BaseResponse.success(processTaskService.getProcessTaskList(processTaskRequest));
    }

}
