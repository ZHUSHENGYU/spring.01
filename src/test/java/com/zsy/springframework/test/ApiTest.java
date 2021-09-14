package com.zsy.springframework.test;

import com.zsy.springframework.context.support.ClassPathXmlApplicationContext;
import com.zsy.springframework.stereotype.Component;
import com.zsy.springframework.test.bean.IUserService;
import com.zsy.springframework.utils.ClassUtils;
import org.junit.Test;

import java.util.Set;

public class ApiTest {

    @Test
    public void testScan() {

        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring-04.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());


    }

    @Test
    public void testBasePackageScan() {

        Set<Class<?>> classes = ClassUtils.scanPackageByAnnotation("com.zsy.springframework.test.bean", Component.class);
        for (Class<?> clazz: classes) {
            System.out.println(clazz);
        }
    }
}
