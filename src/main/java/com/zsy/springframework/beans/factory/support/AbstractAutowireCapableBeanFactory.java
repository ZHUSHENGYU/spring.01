package com.zsy.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zsy.springframework.beans.BeansException;
import com.zsy.springframework.beans.PropertyValue;
import com.zsy.springframework.beans.PropertyValues;
import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.beans.factory.config.BeanReference;
import com.zsy.springframework.beans.factory.support.strategy.CglibSubclassingInstantiationStrategy;
import com.zsy.springframework.beans.factory.support.strategy.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyBeanPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected void applyBeanPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {


        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues())
            {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            } } catch (Exception e) {throw new BeansException("Error setting property values：" + beanName);
        }

    }

    private Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {

        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors()
                ;
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}