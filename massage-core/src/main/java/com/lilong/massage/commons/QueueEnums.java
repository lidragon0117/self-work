package com.lilong.massage.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : lilong
 * @date : 2024-12-29 16:24
 * @description : 队列枚举类
 */
@Getter
@AllArgsConstructor
public enum QueueEnums {
    MESSAGE_QUEUE("message.queue", "消息队列","");
    private String code;
    private String value;
    private String desc;

}
