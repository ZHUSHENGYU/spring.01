package com.zsy.springframework.beans.factory.config;

public interface DisposableBean {

    void destroy() throws Exception;
}
