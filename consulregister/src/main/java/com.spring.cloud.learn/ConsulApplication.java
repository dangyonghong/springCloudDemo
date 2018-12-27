package com.spring.cloud.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//服务发现注解
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class,args);
    }
}
