package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object[] args) throws BeansException;

    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;
}
