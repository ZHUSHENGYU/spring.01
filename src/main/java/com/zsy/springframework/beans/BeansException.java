package com.zsy.springframework.beans;

public class BeansException extends RuntimeException {

    private String msg;

    public BeansException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BeansException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
