package com.zsy.springframework.beans.factory.config;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
