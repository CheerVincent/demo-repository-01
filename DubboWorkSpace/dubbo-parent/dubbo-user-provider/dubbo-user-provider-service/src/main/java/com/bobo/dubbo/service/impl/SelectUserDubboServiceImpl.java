package com.bobo.dubbo.service.impl;

import com.bobo.dubbo.service.SelectUserDubboService;
import com.bobo.mapper.UserMapper;
import com.bobo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectUserDubboServiceImpl implements SelectUserDubboService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUser() {
        return userMapper.queryAll();
    }
}
