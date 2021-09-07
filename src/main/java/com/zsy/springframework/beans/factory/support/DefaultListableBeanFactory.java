package com.zsy.springframework.beans.factory.support;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends  AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {

        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {

        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {

        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                result.put(beanName, (T)getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {

        beanDefinitionMap.keySet().forEach((beanName) -> {
            getBean(beanName);
        });
    }
}
