package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;

public interface ConfigurableListableBeanFactory extends AutowiredCapableBeanFactory,BeanFactory, ListableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws BeansException;
}
