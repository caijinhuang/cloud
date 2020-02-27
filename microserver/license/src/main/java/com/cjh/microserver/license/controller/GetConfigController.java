package com.cjh.microserver.license.controller;

import com.cjh.microserver.license.service.api.LicenseHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cjh
 * @date 2019/12/29 17:42
 **/
@Controller
@Log4j2
@RequestMapping("/v1/configs")
public class GetConfigController {

    @Value("${profiles.default}")
    String configInfo;

    @Autowired
    LicenseHandleService licenseHandleService;

    @ResponseBody
    @GetMapping("/get")
    public String getConfig() {
        log.info("test");
        log.info("info:{}", configInfo);
        log.info("info:yes");
        return ">>>>>>>>yes";
    }

    @ResponseBody
    @GetMapping("/receive")
    public String receive() {
        log.info("接收请求！");
        String result = licenseHandleService.licenseHandleByTmp();
        log.info(">>>>>>>receive:打印结果：{}", result);
        return result;
    }
}
