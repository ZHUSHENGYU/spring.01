package com.zsy.springframework.context.annotation;

import com.zsy.springframework.beans.factory.config.BeanDefinition;
import com.zsy.springframework.utils.ClassUtils;

import java.util.HashSet;
import java.util.Set;

public class ClasspathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new HashSet<>();
        Set<Class<?>> classes = ClassUtils.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz: classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
