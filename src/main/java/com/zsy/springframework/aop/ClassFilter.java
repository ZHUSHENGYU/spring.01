package com.zsy.springframework.aop;

public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
