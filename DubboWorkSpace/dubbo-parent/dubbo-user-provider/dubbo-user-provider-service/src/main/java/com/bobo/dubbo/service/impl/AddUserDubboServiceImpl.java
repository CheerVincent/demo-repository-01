package com.bobo.dubbo.service.impl;

import com.bobo.dubbo.service.AddUserDubboService;
import com.bobo.mapper.UserMapper;
import com.bobo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "addUserDubboServiceImpl")
public class AddUserDubboServiceImpl implements AddUserDubboService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insertUser(user);
    }
}
