package com.aspect.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author baifan, 2018/9/28
 */
public class Test {

    public static void main(String args[]) {
        // 创建RealSubject
        Programer programer = new Programer();
        // 创建handler，用于代理RealSubject的请求
        Hacker hacker = new Hacker();
        // cglib中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        // 设置programer为要代理的类
        enhancer.setSuperclass(programer.getClass());
        // 设置回调，设置后，所有给programer的请求，都会请求callback，而callback则需要实行intercepter()方法进行拦截
        enhancer.setCallback(hacker);
        Programer proxy = (Programer) enhancer.create();

        proxy.code();
    }
}
