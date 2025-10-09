package Multithreading_Concepts;

import java.util.concurrent.CountDownLatch;

class worker extends Thread{
    private CountDownLatch latch ;
    private String TaskName;
    worker(CountDownLatch latch , String TaskName){
        this.latch = latch;
        this.TaskName = TaskName;
    }
     public void run(){
        try{
            System.out.println(TaskName + " Intialization started ");
            Thread.sleep((int)(Math.random()*2000)+100);
            System.out.println( TaskName + "<-Task completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            latch.countDown();
            System.out.println("Remaining task to complete : " + latch.getCount());
        }
    }
}
public class CountDownLatchDemo {
    public static void main(String[] args) {
      CountDownLatch latch  =new CountDownLatch(3);
      worker w1 = new worker(latch , "loading configuration");
      worker w2  = new worker(latch , "connecting to databases");
      worker w3 = new worker(latch , "fetching data from database");

      w1.start();
      w2.start();
      w3.start();
        System.out.println("Main Thread waiting for tasks to complete....");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks are completed");
        System.out.println("Now main thread can proceed with application ");
    }
}
