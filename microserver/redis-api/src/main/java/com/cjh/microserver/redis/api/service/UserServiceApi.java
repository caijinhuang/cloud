package com.cjh.microserver.redis.api.service;

import com.cjh.common.exception.dto.SysInvocationException;
import com.cjh.common.response.dto.ResponseResult;
import com.cjh.microserver.redis.api.dto.UserReq;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/9 20:32
 **/
public interface UserServiceApi {

    /**
     * 查询用户信息
     * @param userId 用户ID
     * @return result
     */
    ResponseResult queryUserInfo(Integer userId);

    /**
     * 保存用户信息
     * @param userReq 用户对象信息
     * @return result
     */
    Map save(@Valid UserReq userReq);

    int putName(String name);

    String getName();

}
