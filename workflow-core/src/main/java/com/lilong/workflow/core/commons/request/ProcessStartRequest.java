package com.lilong.workflow.core.commons.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-06 22:18
 * @description :
 */
@Data
public class ProcessStartRequest {
    /**
     * 流程key
     */
    private String processKey;
    /**
     * 业务编码
     */
    private String businessKey;
    /**
     * 其余流程中要展示的参数
     */
    Map<String,Object> variables=new HashMap<String,Object>();

}
