package com.zsy.springframework.beans.factory.config;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
