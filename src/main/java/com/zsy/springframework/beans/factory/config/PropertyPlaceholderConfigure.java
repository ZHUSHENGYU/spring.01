package com.zsy.springframework.beans.factory.config;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zsy.springframework.core.io.Resource;
import com.zsy.springframework.core.io.support.DefaultResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigure implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        try {

            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName: beanDefinitionNames) {

                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue: propertyValues.getPropertyValues()) {

                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;

                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = buffer.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx+2, stopIdx);
                        String propValue = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx+1,propValue);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {

            throw new BeansException("Could not load properties", e);
        }

    }
}
