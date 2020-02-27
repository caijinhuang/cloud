package com.cjh.microserver.socket.controller;

import com.cjh.microserver.socket.service.api.NetworkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjh
 * @date 2020/1/30 21:05
 **/
@RestController
@RequestMapping("/v1/net")
public class SocketController {

    @Autowired
    NetworkService networkService;

    @GetMapping("/baseInfo/get")
    @ApiOperation(value = "获取网络信息", notes = "备注3", tags = {"socket-base"})
    @ResponseBody
    public String getBaseInfo() {
        networkService.getNetInfo();
        return "success";
    }

    @GetMapping("/net/start")
    @ApiOperation(value = "启动自定义的web服务器", notes = "访问：http:localhost:6767", tags = {"socket-base"})
    @ResponseBody
    public String startServer() {
        networkService.startMyWeb();
        return "success";
    }
}
