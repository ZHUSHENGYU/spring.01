package com.zsy.springframework.beans.factory.support.reader;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.BeanDefinitionRegistry;
import com.zsy.springframework.core.io.Resource;
import com.zsy.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
}
