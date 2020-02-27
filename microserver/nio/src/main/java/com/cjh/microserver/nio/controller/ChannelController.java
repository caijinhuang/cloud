package com.cjh.microserver.nio.controller;

import com.cjh.microserver.nio.service.api.NioService;
import com.cjh.microserver.nio.service.impl.MultiNioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author cjh
 * @date 2020/1/27 16:53
 **/
@RestController
@RequestMapping("/v1/channel")
public class ChannelController {

    @Autowired
    NioService nioService;
    @Autowired
    MultiNioService multiNioService;

    @GetMapping("/file/write")
    @ApiOperation(value = "写文件", notes = "备注3", tags = {"nio-file"})
    @ResponseBody
    public String fileWrite() {
        nioService.fileWrite();
        return "success";
    }

    @GetMapping("/file/read")
    @ApiOperation(value = "读文件", notes = "备注3", tags = {"nio-file"})
    @ResponseBody
    public String fileRead() {
        nioService.fileRead();
        return "success";
    }

    @GetMapping("/file/readFromBuffer")
    @ApiOperation(value = "从缓冲区中读已关闭通道的文件内容", notes = "备注3", tags = {"nio-file"})
    @ResponseBody
    public String readFromBuffer() throws IOException {
        nioService.readFromBuffer();
        return "success";
    }

    @GetMapping("/file/multithreadingUseChannel")
    @ApiOperation(value = "多线程写入", notes = "备注3", tags = {"nio-file"})
    @ResponseBody
    public String multithreadingUseChannel() throws FileNotFoundException {
        nioService.multithreadingUseFileChannel();
        return "success";
    }

    @GetMapping("/file/multithreadingRead")
    @ApiOperation(value = "多线程读取", notes = "备注3", tags = {"nio-file"})
    @ResponseBody
    public String multithreadingRead() throws IOException, InterruptedException {
        multiNioService.execRead();
        return "success";
    }
}
