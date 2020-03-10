package com.cjh.common.exception.constants;

import lombok.AllArgsConstructor;

/**
 * 响应异常
 *
 * @author cjh
 * @date 2020/3/9 9:46
 **/
@AllArgsConstructor
public enum BusinessErrorCode implements ErrorCode {
    /**
     * 参数异常
     */
    ARGS_ERROR("1001", "参数异常！"),
    DB_OPERATION_EXCEPTION("1002", "数据库操作异常！");

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
        return BUSINESS + this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
