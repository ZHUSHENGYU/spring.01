package com.zsy.springframework.context.support.event;

public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
