package com.zsy.springframework.context.support;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zsy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;
import com.zsy.springframework.context.ConfigurableApplicationContext;
import com.zsy.springframework.core.io.support.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {

        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        invokeBeanFactoryPostProcessors(beanFactory);

        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor: beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        Map<String, BeanPostProcessor> beanPostProcessorMap =
                beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object[] args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBeansOfType(requiredType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {

        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {

        getBeanFactory().destroySingletons();
    }
}
