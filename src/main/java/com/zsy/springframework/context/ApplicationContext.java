package com.zsy.springframework.context;

import com.zsy.springframework.beans.factory.ListableBeanFactory;
import com.zsy.springframework.context.support.event.ApplicationEventPublisher;

public interface ApplicationContext extends ListableBeanFactory, ApplicationEventPublisher {


}
