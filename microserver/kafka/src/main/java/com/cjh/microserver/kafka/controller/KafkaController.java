package com.cjh.microserver.kafka.controller;

import com.cjh.microserver.kafka.service.api.KafkaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjh
 * @date 2020/1/22 15:17
 **/
@RestController
@RequestMapping("/v1/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/send")
    @ApiOperation(value = "发送消息", notes = "备注3", tags = {"Kafka"})
    @ResponseBody
    public String sendMsg(String msg) {
        kafkaService.send(msg);
        return "success";
    }
}
