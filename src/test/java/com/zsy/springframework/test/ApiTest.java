package com.zsy.springframework.test;

import com.zsy.springframework.context.support.ClassPathXmlApplicationContext;
import com.zsy.springframework.stereotype.Component;
import com.zsy.springframework.test.bean.IUserService;
import com.zsy.springframework.utils.ClassUtils;
import org.junit.Test;

import java.util.Set;

public class ApiTest {

    @Test
    public void test() {

        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring-04.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
