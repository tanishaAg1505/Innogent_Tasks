import java.util.*;
class EvenOdd {
    private int start = 1;
    private int end;

    EvenOdd(int end) {
        this.end = end;
    }

    public synchronized void printodd() throws InterruptedException {
        while (start <= end) {
            if (start % 2 == 0) {
                wait();
            } else {
                System.out.println("Thread 1 (odd) : " + start);
                start++;
                notify();
            }
        }
    }

    public synchronized void printeven() throws InterruptedException {
        while (start <= end) {
            if (start % 2 == 1) {
                wait();
            } else {
                System.out.println("Thread 2 (even) : " + start);
                start++;
                notify();
            }
        }
    }
}
public class EvenOddProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the limit : ");
        int limit = sc.nextInt();
        EvenOdd obj = new EvenOdd(limit);
        Thread t1 = new Thread(()-> {
            try {
                obj.printodd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()-> {
            try {
                obj.printeven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }
}