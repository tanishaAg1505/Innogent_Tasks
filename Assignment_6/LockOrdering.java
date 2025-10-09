package Multithreading_Concepts;
 class Resources{
String resource;
 Resources(String resource){
     this.resource = resource;
 }
}
public class LockOrdering {
    public static void main(String[] args) {
        Resources r1 = new Resources("Resource 1");
        Resources r2 = new Resources("Resources 2");

        Thread thread1 = new Thread(()->{
            synchronized (r1){
                System.out.println("Thread 1 locked : " + r1.resource);
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (r2){
                    System.out.println("Thread 1 locked : " + r2.resource);
                }
            }
        });
        Thread thread2 = new Thread(()->{
            synchronized (r1){
                System.out.println("Thread 2 locked : " + r1.resource);
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (r2){
                    System.out.println("Thread 2 locked : " + r2.resource);
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
