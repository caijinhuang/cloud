package com.cjh.common.response.dto;

import com.cjh.common.exception.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误信息对象
 *
 * @author cjh
 * @date 2020/3/9 9:14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
    public String code;
    public String msg;

    public ErrorInfo(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ErrorInfo(ErrorCode errorCode, String msg) {
        this(errorCode);
        this.msg = msg;
    }
}
