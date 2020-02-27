package com.cjh.microserver.hystrix.service.impl;

import com.cjh.microserver.hystrix.service.api.HystrixService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.log4j.Log4j2;

/**
 * @author cjh
 * @date 2020/1/13 10:34
 **/
@Log4j2
public class HystrixServiceImpl extends HystrixCommand<String> implements HystrixService {

    private String name;

    public HystrixServiceImpl(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));

//        super(HystrixCommand.Setter
//                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
//                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
//                )
//        );
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                ).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(3)
                        .withMaxQueueSize(50)
                        .withMaximumSize(100)
                )
        );
        this.name = name;
    }

    @Override
    public String handle() {
        return null;
    }

    @Override
    protected String run() throws Exception {
//        try {
//            Thread.sleep(1000 * 2);
//        } catch (InterruptedException e) {
//            log.error("休眠异常！", e);
//        }
        log.info("打印结果：{}",this.name);
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了！";
    }

    @Override
    protected String getCacheKey() {
        log.info("缓存显示");
        return this.name;
    }
}
