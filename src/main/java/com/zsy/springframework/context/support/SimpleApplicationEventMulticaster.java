package com.zsy.springframework.context.support;

import com.zsy.springframework.beans.factory.BeanFactory;
import com.zsy.springframework.context.support.event.ApplicationEvent;
import com.zsy.springframework.context.support.event.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {

        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {

        for (ApplicationListener listener: getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
