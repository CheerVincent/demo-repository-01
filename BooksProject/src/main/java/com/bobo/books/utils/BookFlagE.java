package com.bobo.books.utils;

/**
 * 书籍的状态的枚举类型
 */
public enum BookFlagE {

    FREE("空闲",0),BORROW("借阅",1),DOWN("下架",2),OTHER("其他",3);

    public String name;
    public int code;

    private BookFlagE(String name, int code){
        this.code = code;
        this.name = name;
    }
}
