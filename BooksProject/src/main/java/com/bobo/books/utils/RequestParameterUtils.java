package com.bobo.books.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RequestParameterUtils {

    /**
     * 通过反射获取表单的数据到JavaBean对象中
     * @param req
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T> T getRequestParameterForReflect(HttpServletRequest req, Class<T> cls) throws Exception{
        T t = cls.newInstance();
        Map<String, String[]> parameterMap = req.getParameterMap();

        Field[] declaredFields = cls.getDeclaredFields();
        if(declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                String[] values = parameterMap.get(declaredField.getName());
                if(values == null || values.length == 0 ){
                    continue;
                }
                // 如果 表单中的值为空 那么也不用处理
                if(values[0] == null || "".equals(values[0])){
                    continue;
                }
                if(declaredField.getType() == String[].class){
                    // 判断是否是数组
                    declaredField.setAccessible(true); // 放开访问权限
                    try {
                        declaredField.set(t,values);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }
                if(declaredField.getType() == Integer.class){
                    declaredField.setAccessible(true); // 放开访问权限
                    try {
                        declaredField.set(t,Integer.parseInt(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }else if(declaredField.getType() == BigDecimal.class){
                    declaredField.setAccessible(true); // 放开访问权限
                    try {
                        declaredField.set(t,new BigDecimal(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }else  if(declaredField.getType() == Date.class){
                    declaredField.setAccessible(true); // 放开访问权限
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        declaredField.set(t,format.parse(values[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    declaredField.setAccessible(false);
                    continue;
                }
                // 普通的数据类型
                // 判断是否是数组
                declaredField.setAccessible(true); // 放开访问权限
                try {
                    declaredField.set(t,values[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                declaredField.setAccessible(false);
                continue;

            }
        }
        return t;
    }
}
