package com.cjh.microserver.hystrix.service.impl;

import lombok.extern.log4j.Log4j2;

/**
 * @author cjh
 * @date 2020/1/19 17:11
 **/
@Log4j2
public class OnlyInteger {

    private static Integer integer;

    public static Integer getInstance() throws InterruptedException {
        int result = 0;
        if (integer == null) {
            log.info("Int进入同步状态！");
            synchronized (OnlyInteger.class) {
                Thread.sleep(3*1000);
                if (integer == null) {
                    log.info("Int 初始化，{}>>>{}", Thread.currentThread().getName(), Thread.currentThread().getId());
                    integer = 1;
                    return integer;
                }
            }
            result = integer++;
//            log.info("Int 又不需要初始化了,{},{}>>>{}", result, Thread.currentThread().getName(), Thread.currentThread().getId());
        }
        log.info("Int 已经存在， 复用！{},{} >>>{}", result, Thread.currentThread().getName(), Thread.currentThread().getId());
        return integer;
    }
}
