package com.zsy.springframework.beans.factory;

import com.zsy.springframework.beans.BeansException;

public interface ObjectFactory <T> {

    T getObject() throws BeansException;
}
