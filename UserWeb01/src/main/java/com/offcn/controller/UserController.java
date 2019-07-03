package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manageruser")
public class UserController {
    @Autowired
    private UserService userService;

    //获取全部用户数据
    @GetMapping("/")
    public String findAll(Model model){
        Map<String, Object> map = userService.findAll();
        //从map获取用户数据集合
       List<User> list=(List<User>) map.get("list");

       model.addAttribute("page",list);
       model.addAttribute("ProviderVersion",map.get("ProviderVersion"));

       return "user/list";

    }

    //点击add 按钮，跳转到新增用户界面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    //点击保存按钮，保存用户数据
    @PostMapping("/")
    public String save(User user){
        userService.add(user);
        return "redirect:/manageruser/";
    }

    //点击edit连接，跳转到编辑页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long id,Model model){
        User user = userService.findOne(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    //在用户修改页面，点击保存，保存修改后的用户信息
    @PutMapping("/")
    public String update(User user){
        userService.update(user.getId(),user);

        return "redirect:/manageruser/";
    }

    //点击删除连接，删除指定id的用户信息
    @RequestMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/manageruser/";
    }
}
