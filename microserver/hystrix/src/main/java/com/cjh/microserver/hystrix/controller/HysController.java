package com.cjh.microserver.hystrix.controller;

import com.cjh.microserver.hystrix.service.impl.HystrixCollapserImpl;
import com.cjh.microserver.hystrix.service.impl.HystrixServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author cjh
 * @date 2020/1/13 10:33
 **/
@RestController
@RequestMapping("/v1")
@Log4j2
public class HysController extends BaseController {

    @GetMapping("/handle/excute")
    @ApiOperation(value = "同步操作", notes = "备注1", tags = {"HystrixOpt"})
    @ResponseBody
    public String handle(String name) {
        log.info("receive  info:{}", name);
        String result = new HystrixServiceImpl(name).execute();
        log.info("execute result:{}\n", result);
        return result;
    }

    @GetMapping("/handle/queue")
    @ApiOperation(value = "异步操作", notes = "备注2", tags = {"HystrixOpt"})
    @ResponseBody
    public String handleQueue(String name) throws ExecutionException, InterruptedException {
        log.info("receive info:{}", name);
        Future<String> result = new HystrixServiceImpl(name).queue();
        log.info("execute result:{}\n", result.get());
        return result.get();
    }

    @GetMapping("/handle/fallback")
    @ApiOperation(value = "回退操作", notes = "备注3", tags = {"HystrixOpt"})
    @ResponseBody
    public String handleFallback(String name) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            log.info("receive info:{}", name);
            Future<String> result = new HystrixServiceImpl(name).queue();
            log.info("execute result:{}", result.get());
            String result1 = new HystrixServiceImpl(name).execute();
            log.info("execute result1:{}\n", result1);
            return result.get();
        } finally {
            context.close();
        }
    }

    @GetMapping("/handle/merge/request")
    @ApiOperation(value = "请求合并", notes = "备注3", tags = {"HystrixOpt"})
    @ResponseBody
    public String mergeRequest(String name) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<String> f1 = new HystrixCollapserImpl(name).queue();
            Future<String> f2 = new HystrixCollapserImpl(name).queue();
            Future<String> f3 = new HystrixCollapserImpl(name).queue();
            log.info("merge result:{}={}", f1.get(), f2.get());
        } catch (Exception e) {
            log.error("merge failure", e);
        } finally {
            context.close();
        }
        return name;
    }

    @GetMapping("/handle/fusing")
    @ApiOperation(value = "熔断测试", notes = "备注3", tags = {"HystrixOpt"})
    @ResponseBody
    @HystrixCommand(fallbackMethod = "defaultBack", commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")})
    public String fusing(int num) {
        int i = 1 / num;
        return String.valueOf(i);
    }
}
