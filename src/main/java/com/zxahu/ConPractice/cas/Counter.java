package com.zxahu.ConPractice.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author baifan, 2019-01-06
 */
public class Counter {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private int i = 0;

    public static void main(String args[]) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for(int j = 0; j < 10; j++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for(int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for(Thread t : ts) {
            t.start();
        }
        for(Thread t : ts) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 运行结果是 i < atomicI
     * 分析：
     * 因为i 没有做线程安全，在多个线程同时 +1 时，可能出现 +1 多次，但实际i 只加了1次的情况
     */

    private void safeCount() {
        for(;;) {
            int i = atomicI.get();
            boolean succ = atomicI.compareAndSet(i, ++i);
            if(succ) {
                //System.out.println("cas: " + atomicI);
                break;
            }
        }
    }

    private void count() {
        i++;
        //System.out.println("非cas: " + i);
    }
}
