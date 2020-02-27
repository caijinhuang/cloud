package com.cjh.microserver.license.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cjh
 * @date 2019/12/30 18:29
 **/
@Configuration
public class RestTemplateConfig {

    @Bean // 自动扫描
    @LoadBalanced //这个注解的意思是在启动时先加载注册中心的域名列表
    public RestTemplate restTemplate() //这个方法用来发http请求
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
