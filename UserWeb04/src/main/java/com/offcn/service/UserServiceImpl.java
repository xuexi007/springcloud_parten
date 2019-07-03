package com.offcn.service;

import com.offcn.po.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add(User user) {
        System.out.println("新增用户失败");
    }

    @Override
    public Map<String, Object> findAll() {
        Map map=new HashMap();
        map.put("list",new ArrayList());

        map.put("ProviderVersion","熔断被触发，远程调用失败");
        return map;
    }

    @Override
    public void update(Long id, User user) {
        System.out.println("修改发生熔断");
    }

    @Override
    public void delete(Long id) {
        System.out.println("删除发生熔断");
    }

    @Override
    public User findOne(Long id) {
        System.out.println("查询用户数据发生熔断");
        return null;
    }
}
