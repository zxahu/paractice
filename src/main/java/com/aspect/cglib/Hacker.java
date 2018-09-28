package com.aspect.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author baifan, 2018/9/28
 */
public class Hacker implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method,
                            Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("hacker appear...");
        methodProxy.invokeSuper(o, args);
        System.out.println("hacker appear again...");
        return null;
    }
}
