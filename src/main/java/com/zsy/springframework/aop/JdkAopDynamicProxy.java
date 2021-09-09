package com.zsy.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkAopDynamicProxy implements AopProxy, InvocationHandler {

    private final AdvicedSupport adviced;

    public JdkAopDynamicProxy(AdvicedSupport adviced) {
        this.adviced = adviced;
    }

    @Override
    public Object getProxy() {

        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , adviced.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (adviced.getMethodMatcher().matches(method, adviced.getTargetSource().getTarget().getClass())) {

            MethodInterceptor methodInterceptor = adviced.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(adviced.getTargetSource().getTarget(), method, args));
        }

        return method.invoke(adviced.getTargetSource().getTarget(), args);
    }
}
