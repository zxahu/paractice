/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJAdvice {

    @Pointcut("execution(* find*(..))")
    private void aspectJMethod(){}

    @Before("aspectJMethod()")
    public void beforeAdivce(JoinPoint joinPoint){
        System.out.println("----beforeAdvice().invoke----");
    }

    @After(value = "aspectJMethod()" )
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("----afterAdvice().invoke----");
    }

    @AfterThrowing(value = "aspectJMethod()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex){
        System.out.println("----afterThrowingAdvice().invoke----");
        System.out.println("err msg : "+ ex.getMessage());
    }

}
