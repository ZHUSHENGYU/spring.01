package com.zsy.springframework.context.support.aware;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.support.aware.Aware;
import com.zsy.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
