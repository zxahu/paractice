package com.zxahu.spring.aopPrinciple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author baifan, 2019-02-24
 */
public class TestAOP {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/java/com/zxahu/spring/aopPrinciple/aopConf.xml");
        TestTarget testTarget = (TestTarget) applicationContext.getBean("testAOP");
        testTarget.test();
        System.out.println("----------------");
        testTarget.test2();
    }
}
