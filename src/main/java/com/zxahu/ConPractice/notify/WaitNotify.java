package com.zxahu.ConPractice.notify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author baifan, 2019-01-09
 */
public class WaitNotify {

    static boolean flag = true;

    static Object lock = new Object();

    public static void main(String args[]) throws Exception{
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }


    static class Wait implements Runnable {

        public void run() {
            synchronized (lock) {
                // flag = true 表示条件不满足，进入waiting状态等待条件满足
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @" +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait(); // 进入waiting状态(等待队列), 放弃锁
                    } catch (Exception e) {

                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        public void run() {
            synchronized (lock) {
                // 2th
                System.out.println(Thread.currentThread() + " hold lock. notify @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                // 改变条件，使得waitThread满足条件
                flag = false;
                SleepUtil.seconds(5);
            }
            SleepUtil.seconds(1);
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtil.seconds(5);
            }
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
