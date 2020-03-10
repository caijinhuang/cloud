package com.cjh.common.exception.constants;

/**
 * 错误码操作
 *
 * @author cjh
 * @date 2020/3/9 9:52
 **/
public interface ErrorCode {
    /**
     * 系统级
     */
    String SYSTEM = "100";

    /**
     * 业务级
     */
    String BUSINESS = "200";


    /**
     * 获取编码
     * @return
     */
    String getCode();

    /**
     * 获取提示信息
     * @return
     */
    String getMsg();

}
