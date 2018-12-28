package com.cloud.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumer.class,args);
    }
}
