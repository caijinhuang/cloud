package com.cjh.microserver.nio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cjh")
public class NioApplication {

    public static void main(String[] args) {
        SpringApplication.run(NioApplication.class, args);
    }

}
