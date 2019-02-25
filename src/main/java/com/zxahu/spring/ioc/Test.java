package com.zxahu.spring.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author baifan, 2019-02-22
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "com.zxahu.spring.ioc.Person";

        Class clazz = Class.forName(className);
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("setText", String.class);
        method.invoke(person, "hello, world");
        person.print();
    }
}
