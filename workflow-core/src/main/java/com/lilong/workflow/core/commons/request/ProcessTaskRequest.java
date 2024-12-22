package com.lilong.workflow.core.commons.request;

import com.lilong.workflow.core.commons.enums.OperateTypeEnums;
import lombok.Data;

/**
 * @author : lilong
 * @date : 2024-12-08 20:47
 * @description : 获取任务流程
 */
@Data
public class ProcessTaskRequest {
    /**
     * 流程实例Id
     */
    private String processId;
    /**
     * 流程定义key
     */
    private String processKey;
    /**
     * 当前处理人
     */
    private String currentUser;
    /**
     * 新处理人
     */
    private String nextHandleUser;
    /**
     * 任务Id
     */
    private String taskId;
    /**
     * 操作类型  RETURN:归还,CLAIM:拾取
     */
    private OperateTypeEnums operateType;
}
