package com.lilong.workflow.core.commons.response.base;

/**
 * @author : lilong
 * @date : 2024-12-07 22:44
 * @description :
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // 错误代码
    private int errorCode;

    // 构造函数
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
