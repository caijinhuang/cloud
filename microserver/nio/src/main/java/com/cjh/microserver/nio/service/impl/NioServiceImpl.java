package com.cjh.microserver.nio.service.impl;

import com.cjh.microserver.nio.service.api.NioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cjh
 * @date 2020/1/22 17:10
 **/
@Service
@Log4j2
public class NioServiceImpl implements NioService {

    public static final String FILE_PATH = "D:/test.txt";

    private FileOutputStream globalFosRef;
    private FileChannel globalFileChannel;

    @Override
    public void fileRead() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(FILE_PATH, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            log.info("length:{}", bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                StringBuffer stringBuffer = new StringBuffer();
                while (buf.hasRemaining()) {
                    stringBuffer.append((char) buf.get());
                }
                log.info("结果输出：{}", stringBuffer);
                buf.compact();
                bytesRead = fileChannel.read(buf);
                log.info("current length:{}", bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fileWrite() {
        FileOutputStream fosRef = null;
        FileChannel fileChannel = null;
        try {
            fosRef = new FileOutputStream(new File(FILE_PATH));
            fileChannel = fosRef.getChannel();

            ByteBuffer buffer = ByteBuffer.wrap("abcde".getBytes());
            log.info("A fileChannel.position()={}", fileChannel.position());
            log.info("write() 1 返回值：{}", fileChannel.write(buffer));
            log.info("B fileChannel.position()={}", fileChannel.position());
            fileChannel.position(2);
//            buffer.rewind(); // 注意：还原buffer的position为0
            ByteBuffer numBuf = ByteBuffer.wrap("123456".getBytes());
            numBuf.position(3);
            numBuf.limit(5);
            fileChannel.write(numBuf);
            // 然后在当前位置position中再进行写入
            log.info("write() 2 返回值：{}", fileChannel.write(buffer));
            log.info("C fileChannel.position()={}", fileChannel.position());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel.isOpen()) {
                try {
                    fileChannel.close();
                    fosRef.close();
                } catch (IOException e) {
                    log.error("通道关闭异常", e);
                }
            }
        }
    }

    @Override
    public void readFromBuffer() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_PATH));
                FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        // 缓冲区开始存储内容的位置，当前位置下标为1，表示从第二位开始存数据，第一个默认为0。
        byteBuffer.position(1);
        // 缓冲区保存和读取限制只能保存三个字节，即使开辟了100个字节的空间，也只能存三个字节。
        byteBuffer.limit(3);
        fileChannel.read(byteBuffer);
        fileChannel.close();
        fileInputStream.close();

        byteBuffer.rewind();

        for (int i = 0; i < byteBuffer.limit(); i++) {
            byte eachByte = byteBuffer.get();
            if (eachByte == 0) {
                System.out.print("空格");
            } else {
                System.out.print((char) eachByte);
            }
        }
    }


    @Override
    public void multithreadingUseFileChannel() throws FileNotFoundException {
        globalFosRef = new FileOutputStream(new File(FILE_PATH));
        globalFileChannel = globalFosRef.getChannel();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.info("线程：{}", Thread.currentThread().getName());
                try {
                    ByteBuffer buffer = ByteBuffer.wrap((Thread.currentThread().getName() + "abcde\r\n").getBytes());
                    globalFileChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            globalFileChannel.close();
            globalFosRef.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}