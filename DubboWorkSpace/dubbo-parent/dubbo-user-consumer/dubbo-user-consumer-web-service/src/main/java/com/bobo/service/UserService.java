package com.bobo.service;

import com.bobo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    int addUser(User user);

    List<User> queryAll();

    int deletedById(int id);
}
