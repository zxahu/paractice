package com.aspect.jdk;


/**
 * @author baifan, 2018/9/28
 */
public class ElectoricCar implements Vehicle, ReCharge {
    @Override
    public void reCharge() {
        System.out.println("充电中...");
    }

    @Override
    public void drive() {
        System.out.println("驾驶中...");
    }
}
