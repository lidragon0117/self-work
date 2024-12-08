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
     * 部署类型 单个文件部署:singleDeploy  zip压缩包部署:zipDeploy
     */
    private String deployType;
    /**
     * 部署流程编码
     */
    private String deployKey;
    /**
     * 租户编码
     */
    private String tenantId;
}
