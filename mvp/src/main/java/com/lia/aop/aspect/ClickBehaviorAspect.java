package com.lia.aop.aspect;

import android.util.Log;

import com.lia.aop.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect //定义切面类
public class ClickBehaviorAspect {
    private final String TAG = "ClickBehaviorAspect";

    // 1.应用中用到了哪些注解，放到当前的切入点进行处理
    // execution 以方法执行作为切点，触发Aspect类
    // * *(..) 可以处理ClickBehavior类所有的方法
    @Pointcut("execution(@com.lia.aop.annotation.ClickBehavior * *(..))")
    public void behavior() {

    }

    // 2.对切入点处理
    @Around("behavior()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取签名方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法所属的类名
        String className = methodSignature.getDeclaringType().getSimpleName();
        // 获取方法名
        String methodName = methodSignature.getName();
        // 获取方法的注解值(需要统计的行为)
        String funName = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();

        // 统计方法的执行时间，统计用户点击某功能行为，存储到本地上传到服务器
        long begin = System.currentTimeMillis();
        Log.e(TAG, "ClickBehivor Method Start >> ");
        Object result = joinPoint.proceed(); //MainActivity中切面的方法
        long duration = System.currentTimeMillis() - begin;
        Log.e(TAG, "ClickBehivor Method End >> ");
        Log.e(TAG, String.format("统计了：%s功能，在%s类的%s方法，用时%d ms", funName, className, methodName, duration));

        return result;
    }

}
