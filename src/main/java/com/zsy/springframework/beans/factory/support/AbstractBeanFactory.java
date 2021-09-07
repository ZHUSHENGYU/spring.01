package com.zsy.springframework.beans.factory.support;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.BeanFactory;
import com.zsy.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {

        return getBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object[] args) throws BeansException {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
