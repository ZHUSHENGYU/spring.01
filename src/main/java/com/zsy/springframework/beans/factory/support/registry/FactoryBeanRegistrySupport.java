package com.zsy.springframework.beans.factory.support.registry;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    private Map<String, Object> factoryBeanObjectCache =
            new ConcurrentHashMap<>();

    public Object getCachedObjectForFactoryBean(String beanName) {

        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT? object : null);
    }

    public Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {

        if (factory.isSingleton()) {

            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT? object : null);
        } else {

            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {

        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}
