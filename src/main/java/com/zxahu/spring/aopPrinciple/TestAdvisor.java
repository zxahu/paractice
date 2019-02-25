package com.zxahu.spring.aopPrinciple;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * @author baifan, 2019-02-24
 */
public class TestAdvisor implements PointcutAdvisor {

    /**
     * 获取切入点
     * @return
     */
    public Pointcut getPointcut() {
        return new TestPointCut();
    }

    /**
     * 获取通知处理逻辑
     * @return
     */
    public Advice getAdvice() {
        return new TestAfterAdvice();
    }

    public boolean isPerInstance() {
        return false;
    }
}
