package com.lia.inject.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnBaseClick {

    // 事件三要素1 setOnClickListener
    String onBaseClickListener();

    // 事件三要素2 View.OnClickListener
    Class onBaseObjectListener();

    // 事件三要素3 onClick(View v)
    String callBackMethod();
}
