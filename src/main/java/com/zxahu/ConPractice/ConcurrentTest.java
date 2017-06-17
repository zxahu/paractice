/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.ConPractice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTest {

    class Producer{
        private Depot depot;

        public Producer(Depot depot) {
            this.depot = depot;
        }

        public void produce(final int val) {
            depot.produce(val);
            System.out.println(Thread.currentThread().getName() + " produce : " + val);
        }
    }

    class Consumer {
        private Depot depot;

        public Consumer(Depot depot) {
            this.depot = depot;
        }

        public void consume(final int val) {
            depot.consume(val);
            System.out.println(Thread.currentThread().getName() + " consume : " + val);
        }
    }

    class Depot {
        private final int MAX_SIZE = 10;
        private int size;
        private ReentrantLock lock;
        private Condition condition;

        public Depot() {
            this.size = 0;
            lock = new ReentrantLock();
            condition = lock.newCondition();
        }

        public void produce(int val) {
            while(true) {
                try{
                    lock.lock();
                    if(size + val > MAX_SIZE) {
                        condition.await();
                    }
                    this.size += val;
                    System.out.println("produce "+ val +" success, size = " + size);
                    Thread.sleep(1000);
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }

        public void consume(int val) {
            while(true) {
                try{
                    lock.lock();
                    if(size - val < 0) {
                        condition.await();
                    }
                    this.size -= val;
                    System.out.println("consume "+ val + " success, size = " + size);
                    Thread.sleep(1000);
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String args[]) throws Exception{
        ConcurrentTest test = new ConcurrentTest();
        ConcurrentTest.Depot depot = test.new Depot();
        final ConcurrentTest.Producer producer = test.new Producer(depot);
        final ConcurrentTest.Consumer consumer = test.new Consumer(depot);

        new Thread(){
            public void run() {
                producer.produce(5);
            }
        }.start();
        new Thread() {
            public void run() {
                consumer.consume(6);
            }
        }.start();
    }
}
