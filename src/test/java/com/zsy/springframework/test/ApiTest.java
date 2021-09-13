package com.zsy.springframework.test;

import com.zsy.springframework.aop.*;
import com.zsy.springframework.context.support.ClassPathXmlApplicationContext;
import com.zsy.springframework.test.bean.IUserService;
import com.zsy.springframework.test.bean.UserService;
import com.zsy.springframework.test.interceptor.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiTest {

    @Test
    public void testProxyClass() {

        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserService.class}, ((proxy, method, args) -> {

                    return "你被代理了";
                }));
        String rs = userService.queryUserInfo();
        System.out.println(rs);
    }

    @Test
    public void testProxyMethod() {

        Object userService = new UserService();

        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , new Class[]{IUserService.class}, new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });

    }

    @Test
    public void testAop() throws NoSuchMethodException {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.zsy.springframework.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

    }

    @Test
    public void testDynamic() {

        IUserService userService = new UserService();

        AdvicedSupport advicedSupport = new AdvicedSupport();
        advicedSupport.setTargetSource(new TargetSource(userService));
        advicedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advicedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.zsy.springframework.test.bean.IUserService.*(..))"));

        IUserService proxy_jdk = (IUserService) new JdkAopDynamicProxy(advicedSupport).getProxy();
        System.out.println(proxy_jdk.queryUserInfo());

        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advicedSupport).getProxy();
        System.out.println(proxy_cglib.register("aaa"));

    }

    @Test
    public void testAop2() {

        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring-04.xml");
        IUserService userService = applicationContext.getBean("userService111", IUserService.class);
        System.out.println(userService);


    }
}
