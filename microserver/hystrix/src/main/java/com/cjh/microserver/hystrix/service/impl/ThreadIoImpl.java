package com.cjh.microserver.hystrix.service.impl;

import com.cjh.microserver.hystrix.service.api.ThreadIo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author cjh
 * @date 2020/1/16 18:47
 **/
@Service
@Log4j2
public class ThreadIoImpl implements ThreadIo {

    private static final String FILE_PATH = "D:/test.txt";

    @Override
    public void ioFile() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(FILE_PATH));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            log.info("length:{}", bytesRead);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    log.info((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void nioFile() {
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
                    stringBuffer.append(buf.get());
                }
                log.info("结果输出：{}",stringBuffer);
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
    public void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (i < 1) {
//                    TimeUnit.SECONDS.sleep(5);
                    String info = "I'm " + i++ + "-th information from client>>>>>>>"+Thread.currentThread().getName();
                    buffer.clear();
                    buffer.put(info.getBytes());
                    log.info(buffer);
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        log.info(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException  e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void server() {
        log.info("bio server started");
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                log.info("Handling client at {}" , clientAddress);
                in = clntSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    log.info(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
