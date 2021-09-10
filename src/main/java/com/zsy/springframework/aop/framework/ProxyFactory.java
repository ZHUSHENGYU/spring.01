package com.zsy.springframework.aop.framework;

import com.zsy.springframework.aop.AdvicedSupport;
import com.zsy.springframework.aop.AopProxy;
import com.zsy.springframework.aop.Cglib2AopProxy;
import com.zsy.springframework.aop.JdkAopDynamicProxy;

public class ProxyFactory {

    private AdvicedSupport advicedSupport;

    public ProxyFactory(AdvicedSupport advicedSupport) {
        this.advicedSupport = advicedSupport;
    }

    public Object getProxy() {

        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {

        if (advicedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advicedSupport);
        }
        return new JdkAopDynamicProxy(advicedSupport);
    }
}
