package com.zsy.springframework.utils;

import cn.hutool.core.lang.ClassScanner;
import com.zsy.springframework.context.annotation.Component;
import com.zsy.springframework.context.support.event.ApplicationListener;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {

        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {

        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    private static boolean isCglibProxyClassName(String clazzName) {

        return (clazzName != null && clazzName.contains("$$"));
    }

    public static Set<Class<?>> scanPackageByAnnotation(String basePackage, Class<? extends Annotation> annotationClass) {

        return ClassScanner.scanPackageByAnnotation(basePackage, annotationClass);
    }
}
