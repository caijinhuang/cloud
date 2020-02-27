package com.cjh.microserver.nio.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cjh
 * @date 2020/1/30 19:49
 **/
@Service
@Log4j2
public class MultiNioService {
    private static FileInputStream fisRef;
    private static FileChannel fileChannel;

    public void execRead() throws InterruptedException, IOException {
        fisRef = new FileInputStream(new File(NioServiceImpl.FILE_PATH));
        fileChannel = fisRef.getChannel();

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                    int readLength = fileChannel.read(byteBuffer);
                    log.info("读取长度！{}",readLength);
                    while (readLength != -1) {
                        byte[] getByte = byteBuffer.array();
                        log.info(new String(getByte, 0, readLength));
                        byteBuffer.clear();
                        readLength = fileChannel.read(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                    int readLength = fileChannel.read(byteBuffer);
                    while (readLength != -1) {
                        byte[] getByte = byteBuffer.array();
                        log.info(new String(getByte, 0, readLength));
                        byteBuffer.clear();
                        readLength = fileChannel.read(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(3000);
        fileChannel.close();
        fisRef.close();
    }
}
