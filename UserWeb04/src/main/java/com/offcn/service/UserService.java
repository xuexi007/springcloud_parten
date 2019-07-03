package com.offcn.service;

import com.offcn.config.FeignConfig;
import com.offcn.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@FeignClient(value = "USERPROVIDER",configuration = FeignConfig.class,fallback = UserServiceImpl.class)
public interface UserService {

    //新增
    @PostMapping("/user/")
    public void add(User user);

    //获取全部用户数据
    @GetMapping("/user/")
    public Map<String,Object> findAll();

    //修改
    @PutMapping("/user/{id}")
    public  void update(@PathVariable("id") Long id,@RequestBody User user);

    //删除
    @DeleteMapping("/user/{id}")
    public void  delete(@PathVariable("id") Long id);

    //查询指定id
    @GetMapping("/user/{id}")
    public User findOne(@PathVariable("id") Long id);
}
