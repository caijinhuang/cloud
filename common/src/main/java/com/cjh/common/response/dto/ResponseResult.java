package com.cjh.common.response.dto;

import com.cjh.common.response.constants.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常提示信息
 *
 * @author cjh
 * @date 2020/3/9 9:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    /**
     * 响应状态
     * 0：异常
     * 1：成功
     */
    private int status;
    /**
     * 响应结果
     */
    private T data;
    /**
     * 错误提示！
     */
    private ErrorInfo error;

    public void setData(T data) {
        this.data = data;
        status = ResponseStatusEnum.SUCCESS.getCode();
    }

    public static ResponseResult builder(){
        return new ResponseResult();
    }

    public ResponseResult data(T data) {
        setData(data);
        return this;
    }

    public ResponseResult error(ErrorInfo error) {
        setError(error);
        return this;
    }

}
