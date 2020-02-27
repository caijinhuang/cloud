package com.cjh.microserver.access.service.impl;

import com.cjh.microserver.access.service.api.AccessService;
import lombok.extern.log4j.Log4j2;
import com.cjh.microserver.myribbon.api.dto.House;
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
 * @date 2019/12/30 16:03
 **/
@Service
@Log4j2
public class AccessServiceImpl implements AccessService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String handle() {
        List<ServiceInstance> insList = discoveryClient.getInstances("license");
        if (insList.isEmpty()) {
            log.info("无可用服务");
            return "无许可证服务";
        }
        String serviceUrl = String.format("%s/license/v1/configs/get", insList.get(0).getUri().toString());
        log.info("目标地址：{}", serviceUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(serviceUrl, HttpMethod.GET, null, String.class);
        log.info("响应结果：{}", result.getBody());
        return null;
    }

    @Override
    public String handleByTemp() {
        log.info("rest请求！");
        String result = restTemplate.getForObject("http://license/license/v1/configs/get", String.class);
        return result;
    }

    @Override
    public String getHouseData(String name) {
        String result = restTemplate.getForObject("http://ribbon-server/ribbon/v1/house/data/" + name, String.class);
        return result;
    }

    @Override
    public House postHouseData(House house) {
        log.info("开始发起请求");
        ResponseEntity<House> responseEntity = restTemplate.postForEntity("http://ribbon-server/ribbon/v1/house/data/save", house, House.class);
        return responseEntity.getBody();
    }
}
