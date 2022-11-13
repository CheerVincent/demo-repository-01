package com.bobo.service.Impl;

import com.bobo.dubbo.service.AddUserDubboService;
import com.bobo.dubbo.service.DeleteUserDubboService;
import com.bobo.dubbo.service.SelectUserDubboService;
import com.bobo.pojo.User;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 从Spring容器中获取provide中的接口的代理对象
     */
    @Autowired
    public AddUserDubboService addUserDubboService;

    @Autowired
    public SelectUserDubboService selectUserDubboService;

    @Autowired
    public DeleteUserDubboService deleteUserDubboService;

    public int addUser(User user) {
        addUserDubboService.addUser(user);
        return 1;
    }

    public List<User> queryAll() {
        return selectUserDubboService.selectUser();
    }

    public int deletedById(int id) {
        return deleteUserDubboService.deletedById(id);
    }
}
