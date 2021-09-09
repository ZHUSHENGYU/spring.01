package com.zsy.springframework.aop;

public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?>[] getTargetClass() {

        return target.getClass().getInterfaces();
    }
}
