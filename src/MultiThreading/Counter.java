package MultiThreading;

public class Counter {
    private int count=0;

    public void increment(){  // Synchronized method locks the whole method; use when entire method is critical.


        // Synchronized block locks only the critical section; better for performance if only part of method is critical.


        synchronized(this){
            count++;
        }
    }
    public int getCount(){
        return count;
    }
}
