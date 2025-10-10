package MultiThreading;

public class MyThread extends Thread {

    public MyThread(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - priority: " + Thread.currentThread().getPriority() + " - count: "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //NO STRICT RULE JUST HINT

        MyThread l = new MyThread("low");
        MyThread m = new MyThread("medium");
        MyThread h = new MyThread("high");
        l.setPriority(Thread.MIN_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        h.setPriority(Thread.MAX_PRIORITY);
        l.start();
        m.start();
        h.start();
    }
}
