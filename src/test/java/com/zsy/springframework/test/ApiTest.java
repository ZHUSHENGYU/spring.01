package com.zsy.springframework.test;

import com.zsy.springframework.context.support.ClassPathXmlApplicationContext;
import com.zsy.springframework.test.bean.IUserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testScan() {

        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring-04.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());


    }
}
