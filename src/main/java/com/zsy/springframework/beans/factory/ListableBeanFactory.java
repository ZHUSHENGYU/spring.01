package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException;

    String[] getBeanDefinitionNames();
}
