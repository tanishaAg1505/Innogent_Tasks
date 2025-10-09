
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter queue capacity: ");
        int capacity = sc.nextInt();

        // Number of iterations (how many items to produce/consume)
        System.out.print("Enter number of iterations: ");
        int num = sc.nextInt();

        
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);

        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= num; i++) {
                    queue.put(i); // automatically waits if queue full
                    System.out.println("Produced: " + i);
                    Thread.sleep(500); // simulate production delay
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= num; i++) {
                    int value = queue.take(); // automatically waits if queue empty
                    System.out.println("Consumed: " + value);
                    Thread.sleep(800); // simulate consumption delay
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }
}
