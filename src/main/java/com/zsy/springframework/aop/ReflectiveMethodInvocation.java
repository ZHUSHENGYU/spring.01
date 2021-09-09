package com.zsy.springframework.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ReflectiveMethodInvocation implements MethodInvocation {

    protected Object target;
    protected Method method;
    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Object getThis() {

        return target;
    }

    @Override
    public Method getMethod() {

        return method;
    }

    @Override
    public Object[] getArguments() {

        return arguments;
    }

    @Override
    public AccessibleObject getStaticPart() {

        return method;
    }

    @Override
    public Object proceed() throws Throwable {

        return method.invoke(target, arguments);
    }
}
