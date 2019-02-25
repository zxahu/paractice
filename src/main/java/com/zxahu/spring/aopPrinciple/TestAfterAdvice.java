package com.zxahu.spring.aopPrinciple;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author baifan, 2019-02-24
 */
public class TestAfterAdvice implements AfterReturningAdvice {
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after" +  target.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
