package com.zsy.springframework.beans.factory.config;

import com.zsy.springframework.beans.BeansException;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
