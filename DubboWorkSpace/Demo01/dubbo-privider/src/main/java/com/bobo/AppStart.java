package com.bobo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStart {

    public static void main(String[] args) throws Exception{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //UserService userService = (UserService) ac.getBean("userService");
        Thread.currentThread().join();
    }
}
