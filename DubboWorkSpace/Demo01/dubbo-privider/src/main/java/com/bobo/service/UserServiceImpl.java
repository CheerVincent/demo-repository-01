package com.bobo.service;

public class UserServiceImpl implements UserService {
    public String sayHello(String name) {
        System.out.println("服务端执行了：" + name);
        return "HELLO" + name +"[msg]" ;
    }
}
