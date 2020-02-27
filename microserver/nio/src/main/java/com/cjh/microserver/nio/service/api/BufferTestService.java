package com.cjh.microserver.nio.service.api;

import java.lang.reflect.InvocationTargetException;

/**
 * 缓冲区测试练习
 * @author cjh
 * @date 2020/1/25 17:04
 **/
public interface BufferTestService {
    void positionTest();

    /**
     * 内存回收
     */
    void memoryRecovery() throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
