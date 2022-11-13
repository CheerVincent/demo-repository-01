package com.bobo.books.service;

import com.bobo.books.bean.User;

import java.util.List;

/**
 * 用户的Service接口
 */
public interface IUserService {

    public List<User> getUser(User user);

    public Integer addUser(User user);

    public Integer deleteById(Integer id);

    public User queryById(Integer id);

    public Integer updateUser(User user);

    public String checkUserName(String userName);

    public User checkUserNameAndPassword(String userName,String password);
}
