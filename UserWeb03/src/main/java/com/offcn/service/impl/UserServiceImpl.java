package com.offcn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //rest服务调用工具类
    @Autowired
    private RestTemplate restTemplate;



 //定义服务名称
    String url="http://USERPROVIDER"+"/user/";

    @Override
    public void add(User user) {
        String returnStr = restTemplate.postForObject(url, user, String.class);
        System.out.println("调用远程接口，新增用户,返回结果:"+returnStr);

    }

    @Override
    @HystrixCommand(fallbackMethod = "findAllCallBack")
    public Map<String,Object> findAll() {
        long begin = System.currentTimeMillis();
        Map map = restTemplate.getForObject(url, Map.class);
        long end = System.currentTimeMillis();
        System.out.println("服务调用时间："+(end-begin));
        return map;
    }
    //定义一个熔断触发后，快速返回方法
    public Map<String,Object> findAllCallBack(){
        Map map=new HashMap();
        map.put("list",new ArrayList());

        map.put("ProviderVersion","熔断被触发，远程调用失败");
        return map;
    }

    @Override
    public void update(Long id, User user) {
       restTemplate.put(url+id,user);
    }

    @Override
    public void delete(Long id) {
      restTemplate.delete(url+id);
    }

    @Override
    public User findOne(Long id) {
        return restTemplate.getForObject(url+id,User.class);
    }
}
