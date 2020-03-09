package com.cjh.microserver.redis.controller;

import com.cjh.common.response.dto.ResponseResult;
import com.cjh.microserver.redis.api.dto.UserReq;
import com.cjh.microserver.redis.api.service.UserServiceApi;
import com.cjh.microserver.redis.data.model.UserInfo;
import com.cjh.microserver.redis.service.api.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author cjh
 * @date 2020/3/9 20:27
 **/
@RestController
@RequestMapping("/v1/user")
public class UserController implements UserServiceApi {

    @Autowired
    UserService userService;

    @Override
    @GetMapping("/query/{userId}")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", tags = {"user"})
    @ResponseBody
    public ResponseResult queryUserInfo(@PathVariable Integer userId) {
        UserInfo userInfo = userService.getUserInfo(userId);
        return ResponseResult.builder().data(userInfo);
    }

    @Override
    @PostMapping("/save")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息", tags = {"user"})
    @ResponseBody
    public ResponseResult save(@RequestBody @Valid UserReq userReq) {
        boolean success = userService.saveUser(userReq);
        return ResponseResult.builder().data("保存成功！");
    }
}
