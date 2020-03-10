package com.cjh.common.exception;

import com.cjh.common.exception.constants.BusinessErrorCode;
import com.cjh.common.exception.constants.SysErrorCode;
import com.cjh.common.exception.dto.SysInvocationException;
import com.cjh.common.response.dto.ErrorInfo;
import com.cjh.common.response.dto.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 异常处理类对象
 *
 * @author cjh
 * @date 2020/3/9 9:01
 **/
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Valid注解异常信息格式化
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        String[] errorInfo = new String[allErrors.size()];
        for (int i = 0; i < allErrors.size(); i++) {
            FieldError error = (FieldError) allErrors.get(i);
            String field = error.getField();
            errorInfo[i] = "【" + field + "】" + error.getDefaultMessage();
        }
        String logInfo = "参数异常：" + StringUtils.join(errorInfo, "__");
        log.error("入参异常:{}", logInfo);
        return ResponseResult.builder().error(new ErrorInfo(BusinessErrorCode.ARGS_ERROR, logInfo));
    }


    /**
     * 运行时异常捕获
     *
     * @param exception 未知异常捕获
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult unKnownException(RuntimeException exception) {
        log.error("未知异常捕获：", exception);
        return ResponseResult.builder().error(SysErrorCode.UNKNOWN_ERROR);
    }

    /**
     * 业务异常捕获
     *
     * @param exception 异常信息
     * @return
     */
    @ExceptionHandler(SysInvocationException.class)
    public ResponseResult sysInvocationException(SysInvocationException exception) {
        log.error("业务执行异常：{}", exception.getError().getMsg());
        if (exception.getCause() != null) {
            log.error(exception.getCause());
        }
        return ResponseResult.builder().error(exception.getError());
    }
}