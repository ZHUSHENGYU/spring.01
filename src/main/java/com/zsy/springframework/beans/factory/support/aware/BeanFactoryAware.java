package com.zsy.springframework.beans.factory.support.aware;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
