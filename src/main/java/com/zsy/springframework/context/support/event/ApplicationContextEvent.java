package com.zsy.springframework.context.support.event;

import com.zsy.springframework.context.ApplicationContext;

public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {

        return (ApplicationContext) getSource();
    }

}
