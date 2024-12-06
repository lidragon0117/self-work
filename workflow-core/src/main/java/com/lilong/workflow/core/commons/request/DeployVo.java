package com.lilong.workflow.core.commons.request;

import lombok.Data;

/**
 * @author : lilong
 * @date : 2024-12-02 23:07
 * @description : 单个流程部署VO
 */
@Data
public class DeployVo {
    /**
     * 文件路径
     */
    private String resourcePath;
    /**
     * 文件名称
     */
    private String resourceName;
    /**
     * 部署类型
     */
    private String deployType;
}
