package com.lia.eventbus;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventBus {

    // 管理类 负责将Activity的方法添加到EventBus中，其他方法通过寻找来调用
    private static volatile EventBus instance;

    // 定义存放方法的容器
    private Map<Object, List<SubscribleMethod>> cacheMap;

    private EventBus(){
        cacheMap = new HashMap<>();
    }

    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void register(Object obj) {
        // 寻找多月obj类，带有subscrible注解的方法
        List<SubscribleMethod> list = cacheMap.get(obj);
        if (list == null) {
            list = findSubscribleMethod(obj);
            cacheMap.put(obj, list);
        }
    }

    // 通过反射拿到所有Subscrible注解的方法添加到容器中 运行时
    private List<SubscribleMethod> findSubscribleMethod(Object obj) {
        List<SubscribleMethod> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            String name = clazz.getName();
            // 判断系统的类break
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                // 只找带有Subscrible注解的方法
                Subscrible subscrible = method.getAnnotation(Subscrible.class);
                if (subscrible == null) {
                    continue;
                }
                // 判断带有subscrible注解方法中的参数类型
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e("错误", "eventbus only accept one para");
                }
                ThreadMode threadModes = subscrible.threadMode();
                SubscribleMethod sbm = new SubscribleMethod(method, threadModes, types[0]);
                list.add(sbm);

            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }

    public void post(Object type) {
        // 直接遍历cacheMap所有的方法
        Set<Object> set = cacheMap.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            List<SubscribleMethod> list = cacheMap.get(obj);
            for (SubscribleMethod subscribleMethod : list) {
                //
                if (subscribleMethod.getType().isAssignableFrom(type.getClass())) {
                    invoke(subscribleMethod, obj, type);
                }
            }
        }
    }

    private void invoke(SubscribleMethod subscribleMethod, Object obj, Object type) {
        Method method = subscribleMethod.getmMethod();
        try {
            method.invoke(obj, type);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
