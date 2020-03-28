package com.cjh.microserver.service.impl;

import com.cjh.common.exception.constants.BusinessErrorCode;
import com.cjh.common.exception.dto.SysInvocationException;
import com.cjh.common.utils.BeanUtil;
import com.cjh.microserver.data.dao.UserInfoMapper;
import com.cjh.microserver.data.model.UserInfo;
import com.cjh.microserver.dto.UserReq;
import com.cjh.microserver.service.api.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cjh
 * @date 2020/3/9 20:22
 **/
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @SneakyThrows
    @Override
    public int saveUser(UserReq userReq) {
        try {
            UserInfo userInfo = BeanUtil.copyProperties(userReq, UserInfo.class);
            userInfoMapper.insertSelective(userInfo);
            return userInfo.getUserId();
        } catch (Exception e) {
            throw new SysInvocationException(BusinessErrorCode.DB_OPERATION_EXCEPTION, "用户信息保存失败！", e);
        }
    }
}
