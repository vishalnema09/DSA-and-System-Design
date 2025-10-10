package MultiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {
    private final Lock Lock = new ReentrantLock(true);

    public void accessResource(){
        Lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ " Acquire the lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName()+ " Released the lock");
            Lock.unlock();
        }
    }
    public static void main(String[] args) {
        FairnessLockExample example = new FairnessLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");
        Thread t3 = new Thread(task, "t3");

        try { // just for the understanding the fainess
            t1.start();
            Thread.sleep(50);
            t2.start();
            Thread.sleep(50);
            t3.start();
        }catch (Exception e){

        }
    }
}
