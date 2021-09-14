package com.zsy.springframework.aop.framework.autoproxy;

import com.zsy.springframework.aop.*;
import com.zsy.springframework.aop.advisor.Advisor;
import com.zsy.springframework.aop.framework.ProxyFactory;
import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.BeanFactory;
import com.zsy.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zsy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zsy.springframework.beans.factory.support.aware.BeanFactoryAware;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {

        return Advice.class.isAssignableFrom(beanClass) ||
                Pointcut.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        if (isInfrastructureClass(bean.getClass())) return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors
                = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor: advisors) {

            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) continue;

            AdvicedSupport advicedSupport = new AdvicedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }

            advicedSupport.setTargetSource(targetSource);
            advicedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advicedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advicedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advicedSupport).getProxy();
        }

        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {

        return pvs;
    }
}
