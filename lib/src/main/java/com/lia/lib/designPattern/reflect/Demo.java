package com.lia.lib.designPattern.reflect;

import java.lang.reflect.Method;

public class Demo {

    // 通过反射调用Person类里面的方法
    public static void main(String[] agrs) throws Exception{
        Class<?> clazz = Class.forName("com.lia.lib.designPattern.reflect.Person");
        String value = "Lia";
        Object obj = clazz.getDeclaredConstructor().newInstance();

        String setMethodName = "setName";
        Method setMethod = clazz.getDeclaredMethod(setMethodName, String.class);
        setMethod.invoke(obj, value);
        String getMethodName = "getName";
        Method getMethod = clazz.getDeclaredMethod(getMethodName);
        System.out.println(getMethod.invoke(obj));
    }
}
