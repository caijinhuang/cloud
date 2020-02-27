package com.cjh.microserver.access.config;

import com.cjh.microserver.access.interceptor.MyLoadBalanced;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cjh
 * @date 2019/12/30 18:29
 **/
@Configuration
@Log4j2
public class RestTemplateConfig {

    @MyLoadBalanced
    @Bean
    public RestTemplate restTemplate() //这个方法用来发http请求
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
