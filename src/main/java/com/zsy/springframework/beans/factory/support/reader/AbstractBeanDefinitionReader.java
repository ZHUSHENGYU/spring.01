package com.zsy.springframework.beans.factory.support.reader;

import com.zsy.springframework.beans.factory.config.BeanDefinitionRegistry;
import com.zsy.springframework.core.io.ResourceLoader;
import com.zsy.springframework.core.io.support.DefaultResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
