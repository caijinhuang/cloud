package com.cjh.common.exception.dto;

import com.cjh.common.exception.constants.ErrorCode;
import com.cjh.common.response.dto.ErrorInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义调用异常
 *
 * @author cjh
 * @date 2020/3/10 8:45
 **/
@Data
@NoArgsConstructor
public class SysInvocationException extends Exception {
    private Throwable cause;
    private ErrorInfo error;


    public SysInvocationException(ErrorInfo error) {
        this.error = error;
    }

    public SysInvocationException(ErrorInfo error, String message) {
        this.error = error;
        this.error.setMsg(message);
    }

    public SysInvocationException(Throwable cause, ErrorInfo error) {
        super(cause);
        this.error = error;
    }

    public SysInvocationException(ErrorCode errorCode) {
        this.error = new ErrorInfo(errorCode);
    }

    public SysInvocationException(ErrorCode errorCode,String message) {
        this.error = new ErrorInfo(errorCode);
        this.error.setMsg(message);
    }

    public SysInvocationException(ErrorCode errorCode,String message, Throwable cause) {
        this.error = new ErrorInfo(errorCode);
        this.error.setMsg(message);
        this.cause = cause;
    }

    public SysInvocationException(ErrorCode errorCode,Throwable cause) {
        this.cause = cause;
        this.error = new ErrorInfo(errorCode);
    }

    public SysInvocationException(String code, String msg) {
        this.error = ErrorInfo.builder().code(code).msg(msg).build();
    }
}
