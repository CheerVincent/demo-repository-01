package com.bobo.dubbo;

import com.alibaba.dubbo.container.Main;

public class start {

     /**
     *访问成功
     * @param args
     */
    public static void main(String[] args) {
        //Dubbo提供的启动方法，默认回去classpath：/META-INF/spring/*.xml加载
        Main.main(args);
    }
}
