package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserProvider002Start {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider002Start.class,args);
    }
}
