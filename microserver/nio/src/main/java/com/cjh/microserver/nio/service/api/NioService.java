package com.cjh.microserver.nio.service.api;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author cjh
 * @date 2020/1/22 17:09
 **/
public interface NioService {

    /**
     * fileChannel测试
     */
    void fileRead();

    /**
     * 写文件
     */
    void fileWrite();

    /**
     * 从缓冲区中读取信息
     */
    void readFromBuffer() throws IOException;

    /**
     * 多线程使用通道
     */
    void multithreadingUseFileChannel() throws FileNotFoundException;
}
