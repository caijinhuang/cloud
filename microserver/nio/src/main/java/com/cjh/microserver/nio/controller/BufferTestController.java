package com.cjh.microserver.nio.controller;

import com.cjh.microserver.nio.service.api.BufferTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @author cjh
 * @date 2020/1/25 17:07
 **/
@RestController
@RequestMapping("/v1/buffer")
public class BufferTestController {

    @Autowired
    BufferTestService bufferTestService;

    @GetMapping("/position/test")
    @ApiOperation(value = "position测试", notes = "备注3", tags = {"nio-buffer"})
    @ResponseBody
    public String positionTest() {
        bufferTestService.positionTest();
        return "success";
    }

    @GetMapping("/memory/recovery")
    @ApiOperation(value = "position测试", notes = "备注3", tags = {"nio-buffer"})
    @ResponseBody
    public String recovery() throws InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        bufferTestService.memoryRecovery();
        return "success";
    }
}
