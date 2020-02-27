package com.cjh.microserver.hystrix.controller;

import com.cjh.microserver.hystrix.service.impl.OnlyInteger;
import com.cjh.microserver.hystrix.service.impl.OnlySocketChannel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author cjh
 * @date 2020/1/19 15:05
 **/
@RestController
@RequestMapping("/v1/concurrent")
@Log4j2
public class ConcurrentController {

    @GetMapping("/getInstance")
    @ApiOperation(value = "单例并发测试", notes = "备注1", tags = {"concurrent"})
    @ResponseBody
    public String getInstance() throws IOException, InterruptedException {
        OnlySocketChannel.getInstance();
        return  "success";
    }

    @GetMapping("/getIntegerInstance")
    @ApiOperation(value = "单例并发测试整型对象", notes = "备注1", tags = {"concurrent"})
    @ResponseBody
    public String getIntegerInstance() throws IOException, InterruptedException {
//        OnlyInteger.getInstance();
        OnlySocketChannel.getIntegerInstance();
        return  "success";
    }
}
