package com.zsy.springframework.beans.factory.support;

import com.zsy.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {

        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName, Object singleton) {

        singletonObjects.put(beanName, singleton);
    }
}
