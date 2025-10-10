package MultiThreading;

public class Test {
    public static void main(String[] args) {

        World t1 = new World(); // new state
        t1.start(); // Runnable
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getState());
        for (; ; ) {
            System.out.println("hello");
        }
    }
}
