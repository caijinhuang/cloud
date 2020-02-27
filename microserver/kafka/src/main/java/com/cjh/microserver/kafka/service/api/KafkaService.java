package com.cjh.microserver.kafka.service.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author cjh
 * @date 2020/1/22 15:10
 **/
public interface KafkaService {

    /**
     * 监听
     */
    void listening(ConsumerRecord<?, ?> record);

    /**
     * 推送通知
     * @param msg 通知内容
     */
    void send(String msg);
}
