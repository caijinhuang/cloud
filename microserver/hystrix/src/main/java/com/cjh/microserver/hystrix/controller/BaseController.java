package com.cjh.microserver.hystrix.controller;

import com.cjh.microserver.hystrix.service.api.ThreadIo;
import com.cjh.microserver.hystrix.service.impl.ServerConnect;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cjh
 * @date 2020/1/16 11:23
 **/
@Log4j2
public class BaseController {

    @Autowired
    ThreadIo threadIo;
    @Autowired
    ServerConnect serverConnect;

    public String defaultBack(int num) {
        log.info("服务调用失败，熔断器触发！入参内容：{}", num);
        return "request failure!";
    }
}
