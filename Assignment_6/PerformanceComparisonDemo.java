package Multithreading_Concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class PerformanceComparisonDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(" Parallel Stream   ");
        parallelStreamExample();

        System.out.println("  ExecutorService ");
        executorServiceExample();
    }

    // Parallel Stream method
    public static void parallelStreamExample() {
        long start = System.currentTimeMillis();

        long sum = IntStream.rangeClosed(1, 10_000_000)
                .parallel() // parallel processing
                .mapToLong(n -> (long) n * n)
                .sum();

        long end = System.currentTimeMillis();
        System.out.println("Sum of squares: " + sum);
        System.out.println("Time taken: " + (end - start) + " ms");
    }

    // ExecutorService method
    public static void executorServiceExample() throws Exception {
        int nThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        int range = 10_000_000;
        int chunkSize = range / nThreads;
        List<Future<Long>> futures = new ArrayList<>();

        long start = System.currentTimeMillis();

        // Submit tasks to executor
        for (int i = 0; i < nThreads; i++) {
            int startRange = i * chunkSize + 1;
            int endRange = (i == nThreads - 1) ? range : (i + 1) * chunkSize;
            futures.add(executor.submit(() -> {
                long sum = 0;
                for (int j = startRange; j <= endRange; j++) {
                    sum += (long) j * j;
                }
                return sum;
            }));
        }

        // Combine results
        long totalSum = 0;
        for (Future<Long> f : futures) {
            totalSum += f.get(); // wait for thread to finish
        }

        executor.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Sum of squares: " + totalSum);
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}
