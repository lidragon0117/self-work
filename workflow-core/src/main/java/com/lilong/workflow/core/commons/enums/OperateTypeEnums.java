package com.lilong.workflow.core.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : lilong
 * @date : 2024-12-22 13:21
 * @description : 操作枚举
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnums {
    RETURN("return","return"),
    CLAIM("claim","claim"),;
    private String value;
    private String code;
}
