package com.zxahu.spring.aopPrinciple;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author baifan, 2019-02-24
 */
public class TestPointCut implements Pointcut {

    private static final String METHOD_NAME = "test";

    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            public boolean matches(Method method, Class<?> targetClass) {
                if (METHOD_NAME.equalsIgnoreCase(method.getName())) {
                    return true;
                }
                return false;
            }

            public boolean isRuntime() {
                return true;
            }

            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                if (METHOD_NAME.equalsIgnoreCase(method.getName())) {
                    return true;
                }
                return false;
            }
        };
    }
}
