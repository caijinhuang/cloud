package com.cjh.microserver.socket.service.impl;

import com.cjh.microserver.socket.service.api.NetworkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author cjh
 * @date 2020/1/30 21:02
 **/
@Service
@Log4j2
public class NetworkServiceImpl implements NetworkService {
    @Override
    public void getNetInfo() {
        try {
            Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
            while (networkInterface.hasMoreElements()) {
                NetworkInterface eachNetworkInterface = networkInterface.nextElement();
                log.info("■getName获得网络设备名称={}", eachNetworkInterface.getName());
                log.info("■getDisplayName获得网络设备显示名称={}", eachNetworkInterface.getDisplayName());
                log.info("■getIndex获得网络接口的索引={}", eachNetworkInterface.getIndex());
                log.info("■isUp是否已经开启并运行={}", eachNetworkInterface.isUp());
                log.info("■isLoopback是否为回调接口={}", eachNetworkInterface.isLoopback());
                log.info("\n");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startMyWeb() {
        try {
            ServerSocket serverSocket = new ServerSocket(6767);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String getString = "";
            while (!"".equals(getString = bufferedReader.readLine())) {
                log.info(getString);
            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            outputStream.write("<html><body><a href='http://www.baidu.com'>i am baidu.com welcomeyou! </a></body></html>".getBytes());
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            log.error("服务启动失败！！！", e);
        }
    }


}
