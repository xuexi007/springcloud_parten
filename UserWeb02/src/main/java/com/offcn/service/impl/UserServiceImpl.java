package com.offcn.service.impl;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //rest服务调用工具类
    @Autowired
    private RestTemplate restTemplate;

    //从注册中心查找发现服务工具
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //通过客户端发现服务工具，查找服务
    public String getServerUrl(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("USERPROVIDER");
        //获取ip
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        return "http://"+ip+":"+port+"/user/";

    }

    @Override
    public void add(User user) {
        String returnStr = restTemplate.postForObject(getServerUrl(), user, String.class);
        System.out.println("调用远程接口，新增用户,返回结果:"+returnStr);

    }

    @Override
    public Map<String,Object> findAll() {
        Map map = restTemplate.getForObject(getServerUrl(), Map.class);
        return map;
    }

    @Override
    public void update(Long id, User user) {
       restTemplate.put(getServerUrl()+id,user);
    }

    @Override
    public void delete(Long id) {
      restTemplate.delete(getServerUrl()+id);
    }

    @Override
    public User findOne(Long id) {
        return restTemplate.getForObject(getServerUrl()+id,User.class);
    }
}
