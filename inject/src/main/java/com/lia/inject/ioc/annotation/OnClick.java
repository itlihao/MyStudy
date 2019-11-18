package com.lia.inject.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnBaseClick(onBaseClickListener = "setOnClickListener",
        onBaseObjectListener = View.OnClickListener.class,
        callBackMethod = "onClick")  // 使用在注解之上的注解
public @interface OnClick {

    int value();
}
