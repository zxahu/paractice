package com.zxahu.ConPractice.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author baifan, 2019-01-31
 */
public class ReentrantReadWriteLockTest {

    static Map<String, Object> map = new HashMap<String, Object>();

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Lock readLock = rwl.readLock();

    static Lock writeLock = rwl.writeLock();

    public static final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static final void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        List<Thread> writeList = new ArrayList<Thread>();
        List<Thread> readList = new ArrayList<Thread>();
        for(int i = 0; i< 10; i++ ) {
            Thread t = new Thread(
                    new Runnable() {
                        public void run() {
                            for(int i=0; i < 10; i++) {
                                System.out.println("put: " + put(i +"", i + "_" + Thread.currentThread().getId()));
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            writeList.add(t);
        }

        for(int i = 0; i < 10; i++ ) {
            Thread t = new Thread(
                    new Runnable() {
                        public void run() {
                            for(int i=0; i < 10; i++) {
                                System.out.println("get: " + get("" + i));
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            readList.add(t);
        }

        for(int i=0; i < 10; i++) {
            writeList.get(i).join();
            writeList.get(i).start();
            readList.get(i).join();
            readList.get(i).start();
        }

    }
}
