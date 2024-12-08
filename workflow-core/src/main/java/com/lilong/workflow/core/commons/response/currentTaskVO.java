package com.lilong.workflow.core.commons.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-08 21:04
 * @description : 当前任务信息
 */
@Data
@Builder
public class currentTaskVO {
    /**
     * 名称
     */
   private String name;
    /**
     * 描述
     */
   private String description;
    /**
     * 主要
     */
   private Integer priority;
    /**
     * 处理人
     */
   private String assignee;
    /**
     *
     */
   private Boolean isSuspended;
}
