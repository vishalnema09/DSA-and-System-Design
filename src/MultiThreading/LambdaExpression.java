package MultiThreading;

public class LambdaExpression {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for(int i=1; i<=5; i++){
                System.out.println("Thread-1 count: " + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=1; i<=5; i++){
                System.out.println("Thread-2 count: " + i);
            }
        });

        t1.start();
        t2.start();
    }
}
