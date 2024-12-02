package com.lilong.workflow.core.utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.stereotype.Component;
/**
 * @author : lilong
 * @date : 2024-12-01 19:08
 * @description :
 */
@Component
public class InitDbUtils {

    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);
    }
}
