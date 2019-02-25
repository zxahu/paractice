package com.zxahu.ConPractice.concurrent;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author baifan, 2019-01-15
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {

            public void run() {
                for(int i = 0; i< 10000; i++) {
                    new Thread(new Runnable() {
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
