package com.lia.inject.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 作用于属性之上
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int value() default -1;
}
