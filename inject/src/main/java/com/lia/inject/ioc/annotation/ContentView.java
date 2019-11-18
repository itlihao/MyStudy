package com.lia.inject.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 作用于类之上
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {

    int value() default -1;
}
