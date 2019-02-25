package com.zxahu.ConPractice.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author baifan, 2019-01-20
 */
public class TwinsLockTest {

    public static void main(String args[]) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    //int count = ((TwinsLock) lock).getCountAndLock();
                    //System.out.println(Thread.currentThread().getName() + "获取到锁, count = " + count);
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "获取到锁");
                    try {
                        SleepUtil.seconds(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtil.seconds(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        for(int i = 0; i < 10; i++) {
            SleepUtil.seconds(1);
            System.out.println();
        }

    }

    static class SleepUtil {
        public static final void seconds(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {

            }
        }
    }
}
