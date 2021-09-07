package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;

public interface AutowiredCapableBeanFactory extends ConfigurableBeanFactory {

    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
