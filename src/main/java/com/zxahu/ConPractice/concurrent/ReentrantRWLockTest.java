/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.ConPractice.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantRWLockTest {

    class Repo {

        private int num = 0;

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);

        public void write() throws InterruptedException {
            lock.writeLock().lock();
            num++;
            System.out.println(Thread.currentThread().getName() + " writing!!!    num = " + num);
            lock.writeLock().unlock();
            Thread.sleep(1000);
        }

        private void read() throws InterruptedException {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " reading... num = " + num);
            lock.readLock().unlock();
            Thread.sleep(1000);
        }

        /**
         * 测试写锁最多只能被重入65535次
         */
        private void testWriteReentry() {
            while(true) {
                lock.writeLock().lock();
                num ++;
                System.out.println(Thread.currentThread().getName() + "加锁成功, num = "+ num +", info = " + lock.toString()); // 这里输出65535次后就会报错, 写锁只能最多被重入65535次
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 测试读写锁的使用
     * @param repo
     */
    private void testSimpleRWLock(final ReentrantRWLockTest.Repo repo) {
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    int count = 0;
                    while (count < 1000) {
                        count ++;
                        try {
                            repo.read();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        }
        new Thread() {
            public void run() {
                int count = 0;
                while (count < 1000) {
                    count ++;
                    try {
                        repo.write();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void main(String args[]) {
        System.out.println(7&8);
        ReentrantRWLockTest test = new ReentrantRWLockTest();
        final ReentrantRWLockTest.Repo repo = test.new Repo();
        test.testSimpleRWLock(repo);
        repo.testWriteReentry();
    }
}
