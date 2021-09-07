package com.zsy.springframework.beans.factory.support.strategy;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
