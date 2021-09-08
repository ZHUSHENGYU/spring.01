package com.zsy.springframework.test;

import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zsy.springframework.beans.factory.config.BeanPostProcessor;
import com.zsy.springframework.beans.factory.config.BeanReference;
import com.zsy.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zsy.springframework.beans.factory.support.reader.XmlBeanDefinitionReader;
import com.zsy.springframework.context.support.ClassPathXmlApplicationContext;
import com.zsy.springframework.test.bean.UserDao;
import com.zsy.springframework.test.bean.UserService;
import com.zsy.springframework.test.common.MyBeanFactoryPostProcessor;
import com.zsy.springframework.test.common.MyBeanPostProcessor;
import com.zsy.springframework.test.event.CustomEvent;
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

    @Test
    public void testApplicationContext() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-02.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testApplicationContext2() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-03.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取 Bean 对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        userService01.queryUserInfo();

        UserService userService02 = applicationContext.getBean("userService", UserService.class);
        userService02.queryUserInfo();


// 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);
// 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(userService02 + " 十六进制哈希：" + Integer.toHexString(userService02.hashCode()));
        //System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void testApplcationEvent() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-03.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 5L, "aaa"));
        applicationContext.registerShutdownHook();
    }
}
