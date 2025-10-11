package MultiThreading;

class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName() + " is using pen "+ this + " and trying to use paper " + paper);
        paper.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}
class Paper{
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() + " is using paper "+ this + " and trying to use pen "+ pen);
        pen.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class task1 implements Runnable{
    Pen pen;
    Paper paper;

    public task1(Pen pen , Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}
class task2 implements Runnable{
    Pen pen;
    Paper paper;

    public task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen){
            paper.writeWithPaperAndPen(pen);
        }
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new task1(pen, paper),"Thread-1");
        Thread t2 = new Thread(new task2(pen, paper),"Thread-2");

        t1.start();
        t2.start();
    }
}
