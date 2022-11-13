package com.bobo;

import com.bobo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStart {

    /**
     * 消费者测试代码
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
         UserService userService = (UserService) ac.getBean("userService");
        System.out.println(userService.sayHello("aaa"));
    }
}
