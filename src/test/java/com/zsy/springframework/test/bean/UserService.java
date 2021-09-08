package com.zsy.springframework.test.bean;

import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.factory.config.DisposableBean;
import com.zsy.springframework.beans.factory.config.InitializingBean;
import com.zsy.springframework.beans.factory.support.aware.BeanNameAware;
import com.zsy.springframework.context.ApplicationContext;
import com.zsy.springframework.context.support.aware.ApplicationContextAware;

public class UserService implements
        InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    private String beanName;
    private ApplicationContext applicationContext;

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    public void queryUserInfo() {

        // System.out.println("查询用户信息：" + userDao.queryUserName(uId));
        System.out.println(userDao.queryUserName(uId) + "," + company + "," + location);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        System.out.println(applicationContext);
    }
}
