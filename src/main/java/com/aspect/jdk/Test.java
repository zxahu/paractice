package com.aspect.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author baifan, 2018/9/28
 */
public class Test {

    public static void main(String[] args) {
        ElectoricCar car = new ElectoricCar();
        ClassLoader classLoader = car.getClass().getClassLoader();
        Class[] interfaces = car.getClass().getInterfaces();
        // 构建InvocationHandler，它实现了invoke()方法
        InvocationHandler handler = new InvocationHandlerImpl(car);
        // 构建 Proxy$ElectoricCar实例，
        Object obj = Proxy.newProxyInstance(classLoader, interfaces, handler);

        Vehicle vehicle = (Vehicle) obj;
        vehicle.drive();

        ReCharge reCharge = (ReCharge) obj;
        reCharge.reCharge();
    }
}
