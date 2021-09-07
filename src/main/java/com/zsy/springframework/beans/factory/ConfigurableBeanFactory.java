package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;
import com.zsy.springframework.beans.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws BeansException;

    void destroySingletons();
}
