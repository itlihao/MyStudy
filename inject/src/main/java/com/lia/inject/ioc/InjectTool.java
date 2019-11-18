package com.lia.inject.ioc;

import android.util.Log;

import com.lia.inject.ioc.annotation.BindView;
import com.lia.inject.ioc.annotation.ContentView;
import com.lia.inject.ioc.annotation.OnBaseClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectTool {

    private static final String TAG = InjectTool.class.getSimpleName();

    public static void inject(Object object) {
        injectContentView(object);

        injectBindView(object);

        injectClick(object);
    }

    /**
     * 把一系列点击事件注入到Activity中
     * @param object 传入的Activity对象
     */
    private static void injectClick(final Object object) {
        Class<?> mActivityClass = object.getClass();

        // 获取Activity所有的方法
        Method[] declaredMethods = mActivityClass.getDeclaredMethods();

        for (final Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);

            // 获取方法的注解
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                // 获取注解的类型
                Class<? extends Annotation> annotationType = annotation.annotationType();

                // 判断是否有OnBaseClick
                OnBaseClick onBaseClick = annotationType.getAnnotation(OnBaseClick.class);

                if (null == onBaseClick) {
                    Log.e(TAG, "onBaseClick is null");
                    continue;
                }

                // 获取事件三要素
                String clickListener = onBaseClick.onBaseClickListener();
                Class baseObject = onBaseClick.onBaseObjectListener();
                String callBackMethod = onBaseClick.callBackMethod();

                // 反射去取viewID
                try {
                    Method method = annotationType.getDeclaredMethod("value");
                    method.setAccessible(true);
                    int value = (int) method.invoke(annotation);

                    Method findViewById = mActivityClass.getMethod("findViewById", int.class);
                    // 获取到找到的view
                    Object view = findViewById.invoke(object, value);

                    Method viewMethod = view.getClass().getMethod(clickListener, baseObject);

                    // 动态代理
                    Object proxy = Proxy.newProxyInstance(baseObject.getClassLoader(),
                            new Class[]{baseObject},
                            new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    return declaredMethod.invoke(object, null);
                                }
                            });

                    viewMethod.invoke(view, proxy);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把一系列控件注入到Activity中
     * @param object 传入的Activity对象
     */
    private static void injectBindView(Object object) {
        Class<?> mActivityClass = object.getClass();

        // TODO 获取Activity里面所有的BindView注解，然后遍历调用findViewById方法
        // 拿到Activity里面所有的BindView注解
        Field[] fields = mActivityClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            BindView bindView = field.getAnnotation(BindView.class);

            if (null == bindView) {
                Log.e(TAG, "BindView is null");
                continue;
            }

            // 获取设置的控件ID R.id.btn1
            int viewId = bindView.value();
            // 调用findViewById()
            try {
                Method findViewById = mActivityClass.getMethod("findViewById", int.class);
                // 获取到找到的view
                Object view = findViewById.invoke(object, viewId);

                // 将找到的view赋值给field
                field.set(object, view);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 将布局注入到Activity中
     * @param object 传入的Activity对象
     */
    private static void injectContentView(Object object) {
        Class<?> mActivityClass = object.getClass();

        // 拿到Activity里面的ContentView注解
        ContentView mContentView = mActivityClass.getAnnotation(ContentView.class);

        if (null == mContentView) {
            Log.d(TAG, "ContentView is null");
            return;
        }

        // 拿到用户设置的布局ID
        int layoutId = mContentView.value();
        // 需要执行setContentView() 把布局注入到Activity
        try {
            Method setContentViewMethod = mActivityClass.getMethod("setContentView", int.class);
            setContentViewMethod.invoke(object, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
