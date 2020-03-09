package com.cjh.common.response.dto;

import com.cjh.common.exception.constants.ErrorCodeHandler;
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

    public ErrorInfo(ErrorCodeHandler errorCodeHandler) {
        this.code = errorCodeHandler.getCode();
        this.msg = errorCodeHandler.getMsg();
    }

    public ErrorInfo(ErrorCodeHandler errorCodeHandler, String msg) {
        this(errorCodeHandler);
        this.msg = msg;
    }
}
