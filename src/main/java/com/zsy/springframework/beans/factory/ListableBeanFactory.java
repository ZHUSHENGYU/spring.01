package com.zsy.springframework.beans.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws Exception;

    String[] getBeanDefinitionNames();
}
