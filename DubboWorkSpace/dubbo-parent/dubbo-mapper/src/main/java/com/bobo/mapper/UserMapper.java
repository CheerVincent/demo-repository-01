package com.bobo.mapper;

import  com.bobo.pojo.User;

import java.util.List;

public interface UserMapper {

    /**
     * 一个添加方法
     */
    int insertUser(User user);

    /**
     * 查询方法
     * @return
     */
    List<User> queryAll();

    /**
     * 根据id值删除数据
     * @param id
     * @return
     */
    int deletedById(Integer id);

}
