package com.bobo.books.utils;

/**
 * 操作字符串的工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param msg
     * @return
     */
    public static boolean isEmpty(String msg){
        return msg == null || "".equals(msg);
    }

    /**
     * 判断字符串是否不为空
     * @param msg
     * @return
     */
    public static boolean isNotEmpty(String msg){
        return !(msg == null || "".equals(msg));
    }
}
