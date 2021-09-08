package com.zsy.springframework.context.support.event;

public class ContextCloseEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextCloseEvent(Object source) {
        super(source);
    }
}
