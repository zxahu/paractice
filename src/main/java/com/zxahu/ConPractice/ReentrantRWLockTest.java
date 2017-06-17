/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.ConPractice;

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
    }

    public static void main(String args[]) {
        ReentrantRWLockTest test = new ReentrantRWLockTest();
        final ReentrantRWLockTest.Repo repo = test.new Repo();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
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
                while (true) {
                    try {
                        repo.write();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
