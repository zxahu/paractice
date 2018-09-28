package com.aspect.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author baifan, 2018/9/28
 */
public class InvocationHandlerImpl implements InvocationHandler{

    private ElectoricCar electoricCar;

    public InvocationHandlerImpl(ElectoricCar electoricCar) {
        this.electoricCar = electoricCar;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method = " + method.getName() +  "args = " + args);
        method.invoke(electoricCar, null);
        System.out.println(method.getName() +  " invoke finish");
        return null;
    }
}
