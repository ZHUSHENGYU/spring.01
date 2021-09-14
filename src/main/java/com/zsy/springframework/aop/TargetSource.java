package com.zsy.springframework.aop;

import com.zsy.springframework.utils.ClassUtils;

public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {

        this.target = target;
    }

    public Object getTarget() {

        return target;
    }

    public Class<?>[] getTargetClass() {

        Class<?> clazz = target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz)? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }
}
