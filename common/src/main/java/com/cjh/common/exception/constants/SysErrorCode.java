package com.cjh.common.exception.constants;

import lombok.AllArgsConstructor;

/**
 * 响应异常
 *
 * @author cjh
 * @date 2020/3/9 9:46
 **/
@AllArgsConstructor
public enum SysErrorCode implements ErrorCode {
    /**
     * 异常错误提示枚举
     */
    UNKNOWN_ERROR("001", "系统未知错误！");

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误提示信息
     */
    private String msg;


    @Override
    public String getCode() {
        return SYSTEM + this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
