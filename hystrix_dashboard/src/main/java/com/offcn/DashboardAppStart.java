package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardAppStart {
    public static void main(String[] args) {
        SpringApplication.run(DashboardAppStart.class,args);
    }
}
