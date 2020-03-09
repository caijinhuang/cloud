package com.cjh.microserver.redis.service.impl;

import com.cjh.common.utils.BeanUtil;
import com.cjh.microserver.redis.api.dto.UserReq;
import com.cjh.microserver.redis.data.dao.UserInfoMapper;
import com.cjh.microserver.redis.data.model.UserInfo;
import com.cjh.microserver.redis.service.api.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cjh
 * @date 2020/3/9 20:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean saveUser(UserReq userReq) {
        UserInfo userInfo = BeanUtil.copyProperties(userReq,UserInfo.class);
        int resultCode = userInfoMapper.insertSelective(userInfo);
        return resultCode > 0;
    }
}
