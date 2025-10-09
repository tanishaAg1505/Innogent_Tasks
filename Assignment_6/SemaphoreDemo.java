package Multithreading_Concepts;
import java.util.concurrent.*;
class Parking{
    Semaphore spot =new Semaphore(3) ;
    public void carpark(String carname) {
        try {
            System.out.println(carname+ " is trying to park...");
            spot.acquire(); // tries to get a permit (spot)
            System.out.println(carname + " parked successfully!");

            Thread.sleep(2000);////  allow car parked for some time

            System.out.println(carname + " is leaving the parking lot...");
            spot.release(); // releases the spot
        } catch (InterruptedException e) {
            e.printStackTrace();}
    }
}
public class SemaphoreDemo {
    public static void main(String[] args) {
        Parking park = new Parking();
        for (int i = 1; i <= 5; i++) {
            String carName = "Car-" + i;
            new Thread(() -> park.carpark(carName)).start();
        }
    }
    }
