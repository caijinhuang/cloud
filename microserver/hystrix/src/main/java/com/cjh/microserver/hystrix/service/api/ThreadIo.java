package com.cjh.microserver.hystrix.service.api;

/**
 * @author cjh
 * @date 2020/1/16 18:48
 **/
public interface ThreadIo {
    /**
     * 普通io处理
     */
    void ioFile();

    /**
     * nio处理
     */
    void nioFile();

    /**
     * 客户端
     */
    void client();

    /**
     * 服务端
     */
    void server();
}
