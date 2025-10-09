package Multithreading_Concepts;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedData{
    private String data = "Normal readable data";
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void readData(String threadName){
        lock.readLock().lock();
        try{
            System.out.println(threadName + "is reading " + data);
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }
    public void writeData(String threadName , String newdata){
        lock.writeLock().lock();
        try{
            System.out.println(threadName + " is writing data...");
            Thread.sleep(3000);
            newdata = data;
            System.out.println(threadName + "data changed to " + data);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedData shared = new SharedData();

        // Reader Threads
        Thread t1 = new Thread(() -> shared.readData("Reader1 (thread-1)"));
        Thread t2 = new Thread(() -> shared.readData("Reader2 (thread-2)"));

        // Writer Threads
        Thread t3 = new Thread(() -> shared.writeData("Writer 1(thread-3)", "Updated Data"));
        Thread t4 = new Thread(() -> shared.writeData("Writer2 (thread-4)", "Final Data"));

        // Start Threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
