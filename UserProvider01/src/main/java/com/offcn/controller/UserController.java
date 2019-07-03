package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${ProviderVersion}")
    private String ProviderVersion;

    //查询全部用户信息
    @GetMapping("/")
    public Map<String,Object> findAll(){
        Map<String,Object> map=new HashMap<>();
        List<User> userList = userService.findAll();
        //封装数据集合到map
        map.put("list",userList);
        //封装服务提供方名称
        map.put("ProviderVersion",ProviderVersion);
        System.out.println("服务1  request is coming...");
        //模拟调用超时
       try {
          int time=  new Random().nextInt(1200);
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  map;
    }

    //新增
    @PostMapping("/")
    public String add(@RequestBody User user){
        try {
            userService.add(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //修改
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,@RequestBody User user){
        try {
            userService.update(id, user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //删除
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        try {
            System.out.println("删除id:"+id);
            userService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return  "fail";
        }
    }

    //查询指定id
    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id){
        System.out.println("查询id:"+id);
      return   userService.findOne(id);
    }

}
