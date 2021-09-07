package com.zsy.springframework.test.common;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;
import com.zsy.springframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            ((UserService) bean).setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
