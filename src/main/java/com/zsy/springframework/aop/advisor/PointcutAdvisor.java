package com.zsy.springframework.aop.advisor;

import com.zsy.springframework.aop.Pointcut;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
