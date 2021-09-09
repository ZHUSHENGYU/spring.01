package com.zsy.springframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Cglib2AopProxy implements AopProxy {

    private AdvicedSupport adviced;

    public Cglib2AopProxy(AdvicedSupport adviced) {
        this.adviced = adviced;
    }

    @Override
    public Object getProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(adviced.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(adviced.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvicedIntercepter(adviced));
        return  enhancer.create();
    }

    private static class DynamicAdvicedIntercepter implements MethodInterceptor {

        private final AdvicedSupport adviced;

        public DynamicAdvicedIntercepter(AdvicedSupport adviced) {
            this.adviced = adviced;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            CglibMethodInvocation cglibMethodInvocation =
                    new CglibMethodInvocation(adviced.getTargetSource().getTarget(), method, args, proxy);
            if (adviced.getMethodMatcher().matches(method, adviced.getTargetSource().getTarget().getClass())) {

                return adviced.getMethodInterceptor().invoke(cglibMethodInvocation);
            }

            return cglibMethodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {

            return this.methodProxy.invoke(target, arguments);
        }
    }
}
