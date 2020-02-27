package com.cjh.microserver.hystrix.service.impl;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cjh
 * @date 2020/1/19 14:56
 **/
@Log4j2
public class OnlySocketChannel {

    private static SocketChannel socketChannel;

    private static Integer integer;
    private static AtomicInteger autInteger;

    public static SocketChannel getInstance() throws IOException, InterruptedException {
        if (socketChannel == null) {
            log.info("进入同步状态！");
            synchronized (OnlySocketChannel.class) {
                if (socketChannel == null) {
                    log.info("初始化！");
                    socketChannel = SocketChannel.open();
                    return socketChannel;
                }
                log.info("又不需要初始化了");
            }
        }
        log.info("已  经存在，复用！");
        return socketChannel;
    }

    public static Integer getIntegerInstance() throws InterruptedException {
        int result = 0;
        if (integer == null) {
            log.info("Int进入同步状态！");
            synchronized (OnlySocketChannel.class) {
                if (integer == null) {
                    Thread.sleep(2*1000);
                    log.info("Int 初始化，{}>>>{}", Thread.currentThread().getName(), Thread.currentThread().getId());
                    integer = 1;
                    return integer;
                }
            }
            result = integer++;
//            log.info("Int 又不需要初始化了,{},{}>>>{}", result, Thread.currentThread().getName(), Thread.currentThread().getId());
        }
        log.info("Int 已经存在，复用！{},{}>>>{}", result, Thread.currentThread().getName(), Thread.currentThread().getId());
        return integer;
    }
}
