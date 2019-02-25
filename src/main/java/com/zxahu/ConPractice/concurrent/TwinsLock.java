package com.zxahu.ConPractice.concurrent;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author baifan, 2019-01-20
 */
public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if(count <= 0) {
                throw new IllegalArgumentException("count must > 0");
            }
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for(;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int reduceCount) {
            for(;;) {
                int current = getState();
                int newCount = current + reduceCount;
                if(compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }


    public void lock() {
        sync.tryAcquireShared(1);
    }

    public void unlock() {
        sync.tryReleaseShared(1);
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public Condition newCondition() {
        return null;
    }
}
