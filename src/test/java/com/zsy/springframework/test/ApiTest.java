package com.zsy.springframework.test;

import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;
import com.zsy.springframework.beans.factory.config.BeanReference;
import com.zsy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zsy.springframework.beans.factory.support.reader.XmlBeanDefinitionReader;
import com.zsy.springframework.test.bean.UserDao;
import com.zsy.springframework.test.bean.UserService;
import com.zsy.springframework.test.common.MyBeanFactoryPostProcessor;
import com.zsy.springframework.test.common.MyBeanPostProcessor;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory() {

        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-01.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring-02.xml");

        BeanFactoryPostProcessor beanFactoryPostProcessor =
                new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        BeanPostProcessor beanPostProcessor =
                new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);


        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
