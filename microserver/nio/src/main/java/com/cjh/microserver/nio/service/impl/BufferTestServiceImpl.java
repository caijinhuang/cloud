package com.cjh.microserver.nio.service.impl;

import com.cjh.microserver.nio.service.api.BufferTestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author cjh
 * @date 2020/1/25 17:04
 **/
@Service
@Log4j2
public class BufferTestServiceImpl implements BufferTestService {
    @Override
    public void positionTest() {
        char[] charArray = new char[]{'a', 'b', 'c', 'd'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        log.info("A capacity()={} limit()={} position()={} ", charBuffer.capacity(), charBuffer.limit(), charBuffer.position());
        charBuffer.position(2);
        log.info("B capacity()={} limit()={} position()={} ", charBuffer.capacity(), charBuffer.limit(), charBuffer.position());
        charBuffer.put("z");
        for (int i = 0; i < charArray.length; i++) {
            log.info(charArray[i]);
        }
    }

    @Override
    public void memoryRecovery() throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("A");
        ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        log.info("B");
        byte[] byteArray = new byte[]{1};
        log.info(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buffer.put(byteArray);
        }
        log.info("put end! ");
        Thread.sleep(1000);
        Method cleanerMethod = buffer.getClass().getMethod("cleaner");
        cleanerMethod.setAccessible(true);
        Object returnValue = cleanerMethod.invoke(buffer);
        Method cleanMethod = returnValue.getClass().getMethod("clean");
        cleanMethod.setAccessible(true);
        cleanMethod.invoke(returnValue);
        // 此程序运行的效果就是1秒钟之后立即回收内存
        // 也就是回收“直接缓冲区”所占用的内存
    }
}
