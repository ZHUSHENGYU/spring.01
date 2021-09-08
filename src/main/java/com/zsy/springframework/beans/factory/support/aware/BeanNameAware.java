package com.zsy.springframework.beans.factory.support.aware;

public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
