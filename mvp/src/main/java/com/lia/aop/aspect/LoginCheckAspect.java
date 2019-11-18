package com.lia.aop.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.lia.mvp.LoginActivity;
import com.lia.mvp.utils.SharedPreferencesUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //定义切面类
public class LoginCheckAspect {
    private final String TAG = "LoginCheckAspect";

    // 1.应用中用到了哪些注解，放到当前的切入点进行处理
    // execution 以方法执行作为切点，触发Aspect类
    // * *(..) 可以处理ClickBehavior类所有的方法
    @Pointcut("execution(@com.lia.aop.annotation.LoginCheck * *(..))")
    public void behavior() {

    }

    // 2.对切入点处理
    @Around("behavior()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        boolean isLogin = SharedPreferencesUtil.getBoolData(context, "isLogin", false);
        if (isLogin) { // 从SP获取当前登录状态
            Log.e(TAG, "已经登录");
            return joinPoint.proceed();
        } else {
            Log.e(TAG, "还未登录");
            context.startActivity(new Intent(context, LoginActivity.class));
            return null;
        }
    }

}
