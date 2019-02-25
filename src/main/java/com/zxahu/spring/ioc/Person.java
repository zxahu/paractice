package com.zxahu.spring.ioc;

/**
 * @author baifan, 2019-02-22
 */
public class Person {

    private String text;

    public void setText(String msg) {
        this.text = msg;
    }

    public void print() {
        System.out.println(text);
    }

}
