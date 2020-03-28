package com.cjh.microserver.controller;

import com.cjh.common.response.RestWrap;
import com.cjh.common.response.dto.ResponseResult;
import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.data.model.UserInfo;
import com.cjh.microserver.dto.UserReq;
import com.cjh.microserver.service.api.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/9 20:27
 **/
@RestWrap
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/query/{userId}")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", tags = {"user"})
    @ResponseBody
    public ResponseResult queryUserInfo(@PathVariable Integer userId) {
        UserInfo userInfo = userService.getUserInfo(userId);
        return ResponseResult.builder().data(userInfo);
    }

    @SneakyThrows
    @PostMapping("/save")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息", tags = {"user"})
    @ResponseBody
    public Map save(@RequestBody @Valid UserReq userReq) {
        int id = userService.saveUser(userReq);
        return MapUtil.builder().put("userId", id).build();
    }
}
