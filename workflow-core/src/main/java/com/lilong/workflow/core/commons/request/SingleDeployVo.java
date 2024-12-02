package com.lilong.workflow.core.commons.request;

import lombok.Data;

/**
 * @author : lilong
 * @date : 2024-12-02 23:07
 * @description : 单个流程部署VO
 */
@Data
public class SingleDeployVo {
    /**
     * 文件路径
     */
    private String xmlPath;
    /**
     * 文件名称
     */
    private String xmlName;
}
