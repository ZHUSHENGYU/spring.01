package com.zsy.springframework.beans.factory.config;

import com.zsy.springframework.beans.BeansException;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException;

    boolean containsBeanDefinition(String beanName);
}
