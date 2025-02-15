package com.lilong.massage.core.base.factory;

import com.lilong.massage.core.base.BuildMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author : lilong
 * @date : 2024-12-29 19:59
 * @description :
 */
@Slf4j
@Service
public class MessageQueueStrategyFactory  implements BuildMessageService {
    @Autowired
    private ApplicationContext context;

    /**
     * 存储
     */
    private static Map<String, BuildMessageService> deployMap;

    /**
     * 初始化发布bean到map存储用于策略模式
     */
    @PostConstruct
    public void init(){
        deployMap = context.getBeansOfType(BuildMessageService.class);
    }

    /**
     * 获取类型
     * @param type
     * @return
     */
    public static BuildMessageService getMessageQueue(String type) {
        return deployMap.get(type);
    }
}
