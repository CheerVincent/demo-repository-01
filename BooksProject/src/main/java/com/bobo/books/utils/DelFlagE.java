package com.bobo.books.utils;

/**
 * 是否删除的枚举类型
 */
public enum DelFlagE {

    YES("yes",0),NO("no",1);

    public String name;
    public int code;

    private  DelFlagE(String name,int code){
        this.code = code;
        this.name = name;
    }
}
