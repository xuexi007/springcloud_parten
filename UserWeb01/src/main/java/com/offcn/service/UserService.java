package com.offcn.service;

import com.offcn.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //新增
    public void add(User user);

    //获取全部用户数据
    public Map<String,Object> findAll();

    //修改
    public  void update(Long id, User user);

    //删除
    public void  delete(Long id);

    //查询指定id
    public User findOne(Long id);
}
