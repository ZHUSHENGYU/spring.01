package com.zsy.springframework.test.bean;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.DisposableBean;
import com.zsy.springframework.beans.factory.config.InitializingBean;
import com.zsy.springframework.beans.factory.support.aware.BeanNameAware;
import com.zsy.springframework.context.ApplicationContext;
import com.zsy.springframework.context.support.aware.ApplicationContextAware;

import java.util.Random;

public class UserService implements IUserService{

    @Override
    public String queryUserInfo() {

        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    @Override
    public String register(String userName) {

        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
