package com.cjh.microserver.redis.service.api;

import com.cjh.microserver.redis.api.dto.UserReq;
import com.cjh.microserver.redis.data.model.UserInfo;

/**
 * @author cjh
 * @date 2020/3/9 20:22
 **/
public interface UserService {
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return
     */
    UserInfo getUserInfo(Integer userId);

    /**
     * 保存用户信息
     * @param userReq 用户基础信息
     * @return
     */
    int saveUser(UserReq userReq);
}
