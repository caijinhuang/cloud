package com.cjh.microserver.license.service.impl;

import com.cjh.microserver.license.service.api.LicenseHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author cjh
 * @date 2019/12/30 18:09
 **/
@Service
@Log4j2
public class LicenseHandleServiceImpl implements LicenseHandleService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String licenseHandle() {
        List<ServiceInstance> insList = discoveryClient.getInstances("access");
        if (insList.isEmpty()) {
            log.info("无可用服务");
            return "无许可证服务";
        }
        String serviceUrl = String.format("%s/access/v1/456/rest/handle", insList.get(0).getUri().toString());
        log.info("目标地址：{}", serviceUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(serviceUrl, HttpMethod.GET, null, String.class);
        log.info("响应结果：{}", result.getBody());
        return result.getBody();
    }

    /**
     * 模板调用
     *
     * @return
     */
    @Override
    public String licenseHandleByTmp() {
        String result = restTemplate.getForObject("http://access/access/v1/456/rest/handle", String.class);
        return result;
    }
}
