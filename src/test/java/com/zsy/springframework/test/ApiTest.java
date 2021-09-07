package com.zsy.springframework.test;

import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanReference;
import com.zsy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zsy.springframework.beans.factory.support.reader.XmlBeanDefinitionReader;
import com.zsy.springframework.test.bean.UserDao;
import com.zsy.springframework.test.bean.UserService;
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

}
