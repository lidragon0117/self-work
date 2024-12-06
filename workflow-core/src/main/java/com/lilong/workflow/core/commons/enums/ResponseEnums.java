package com.lilong.workflow.core.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : lilong
 * @date : 2024-12-06 23:32
 * @description :
 */
@Getter
@AllArgsConstructor
public enum ResponseEnums {
    SUCCESS("success", 200, "操作成功"),
    FIAL("error", 500, "操作失败");
    String name;
    Integer code;
    String description;
}
