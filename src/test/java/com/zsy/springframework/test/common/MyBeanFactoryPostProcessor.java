package com.zsy.springframework.test.common;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition userServiceBeanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userServiceBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
