package com.offcn;

import com.offcn.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringCloudApplication
@EnableZuulProxy
public class appstartzull {
    public static void main(String[] args) {
        SpringApplication.run(appstartzull.class,args);
    }
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
