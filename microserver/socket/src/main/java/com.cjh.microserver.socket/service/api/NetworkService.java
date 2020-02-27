package com.cjh.microserver.socket.service.api;

/**
 * @author cjh
 * @date 2020/1/30 20:59
 **/
public interface NetworkService {

    /**
     * 获取网络信息
     */
    void getNetInfo();

    /**
     * 启动自定义web服务
     */
    void startMyWeb();
}
