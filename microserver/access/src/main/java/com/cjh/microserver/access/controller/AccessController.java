package com.cjh.microserver.access.controller;

import com.cjh.microserver.access.service.api.AccessService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cjh
 * @date 2019/12/30 15:57
 **/
@Controller
@Log4j2
@RequestMapping("/v1/{accessId}/rest")
public class AccessController {

    @Autowired
    AccessService accessService;

    @ResponseBody
    @GetMapping("/handle")
    public String accessHandle(@PathVariable(name = "accessId") String accessId) {
        accessService.handleByTemp();
        return accessId;
    }

    @ResponseBody
    @GetMapping("/receive")
    public String receive(@PathVariable(name = "accessId") String accessId) {
        log.info("接收信息：{}", accessId);
        return accessId;
    }

    @ResponseBody
    @GetMapping("/house/data")
    public String getHouseData(@PathVariable(name = "accessId") String accessId) {
        log.info("接收信息：{}", accessId);
        return accessId;
    }
}
