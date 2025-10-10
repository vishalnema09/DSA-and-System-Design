package MultiThreading;

public class MyThread extends Thread {

    public MyThread(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+ " is running");
            Thread.yield();// to give the change to another thread
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread("t1");// change the name of thread
        MyThread t2 = new MyThread("t2"); // change the name of thread
        t1.start();
        t2.start();
    }
}
