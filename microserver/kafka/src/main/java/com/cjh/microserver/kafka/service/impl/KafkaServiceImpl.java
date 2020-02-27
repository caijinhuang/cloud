package com.cjh.microserver.kafka.service.impl;

import com.cjh.microserver.kafka.service.api.KafkaService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cjh
 * @date 2020/1/22 15:13
 **/
@Service
@Log4j2
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    @KafkaListener(topics = "demo")
    public void listening(ConsumerRecord<?, ?> record) {
        log.info("topic = {}, offset = {}, value = {} \n", record.topic(), record.offset(), record.value());
    }

    @Override
    public void send(String msg) {
        log.info("发送消息 ----->>>>>  message = {}", msg );
        kafkaTemplate.send("demo", msg);
    }
}
