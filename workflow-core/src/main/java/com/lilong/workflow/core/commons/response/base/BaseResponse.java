package com.lilong.workflow.core.commons.response.base;

import com.lilong.workflow.core.commons.enums.ResponseEnums;
import lombok.Data;


/**
 * @author : lilong
 * @date : 2024-12-06 23:22
 * @description : 统一消息返回基础类
 */
@Data
public class BaseResponse<T> {
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
    /**
     * code 编码
     */
    private Integer code;

    private BaseResponse() {
    }

    /**
     * 成功返回通知
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResponseEnums.SUCCESS.getCode());
        response.setMessage(ResponseEnums.SUCCESS.getName());
        response.setData(data);
        return response;
    }
    /**
     * 失败返回通知
     *
     * @param message
     * @param code
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> failure(String message, Integer code) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 失败消息模板
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> failure() {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResponseEnums.FIAL.getCode());
        response.setMessage(ResponseEnums.FIAL.getName());
        return response;
    }
    /**
     * 失败消息模板
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> failure(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(ResponseEnums.FIAL.getCode());
        response.setMessage(message);
        return response;
    }
    /**
     * 通用返回通知模板
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> of(Integer code,T data,String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
