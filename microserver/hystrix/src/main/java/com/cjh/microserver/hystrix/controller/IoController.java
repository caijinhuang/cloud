package com.cjh.microserver.hystrix.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程IO测试
 *
 * @author cjh
 * @date 2020/1/16 18:39
 **/
@RestController
@RequestMapping("/v1/thread")
@Log4j2
public class IoController extends BaseController {

    @GetMapping("/ioFile")
    @ApiOperation(value = "普通线程IO文件读取", notes = "备注1", tags = {"ThreadIO"})
    @ResponseBody
    public String IoFile() {
        threadIo.ioFile();
        return "success";
    }

    @GetMapping("/nioFile")
    @ApiOperation(value = "NIO文件读取", notes = "备注1", tags = {"ThreadIO"})
    @ResponseBody
    public String NioFile() {
        threadIo.nioFile();
        return "success";
    }

    @GetMapping("/server")
    @ApiOperation(value = "启动服务端", notes = "备注1", tags = {"ThreadIO"})
    @ResponseBody
    public String StartServer() {
        threadIo.server();
        return "success";
    }

    @GetMapping("/client")
    @ApiOperation(value = "启动客户端", notes = "备注1", tags = {"ThreadIO","nio"})
    @ResponseBody
    public String StartClient() {
        threadIo.client();
        return "success";
    }


    @GetMapping("/nio/server")
    @ApiOperation(value = "NIO 服务端", notes = "备注1", tags = {"nio"})
    @ResponseBody
    public String NioServer() {
        serverConnect.selector();
        return "success";
    }

}
