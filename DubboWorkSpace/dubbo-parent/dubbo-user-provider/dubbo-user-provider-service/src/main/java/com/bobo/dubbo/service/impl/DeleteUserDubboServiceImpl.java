package com.bobo.dubbo.service.impl;

import com.bobo.dubbo.service.DeleteUserDubboService;
import com.bobo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserDubboServiceImpl implements DeleteUserDubboService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deletedById(int id) {
        return userMapper.deletedById(id);
    }
}
