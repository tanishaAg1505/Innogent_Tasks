package Multithreading_Concepts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // Step 1: Directory path
        File folder = new File("C:\\Users\\harsh\\Downloads\\textfiles");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No text files found in the directory!");
            return;
        }

        // Step 2: Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<Integer>> results = new ArrayList<>();

        // Step 3: Submit Callable tasks for each file
        for (File file : files) {
            Callable<Integer> task = () -> {
                int lineCount = 0;
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    while (br.readLine() != null) {
                        lineCount++;
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + file.getName());
                }
                System.out.println("File " + file.getName() + " has " + lineCount + " lines.");
                return lineCount;
            };

            results.add(executor.submit(task));
        }

        // Step 4: Collect results
        int totalLines = 0;
        for (Future<Integer> future : results) {
            try {
                totalLines += future.get();  // waits until task completes
            } catch (Exception e) {
                System.out.println("Error getting result: " + e.getMessage());
            }
        }

        // Step 5: Display total
        System.out.println("Total number of lines in all text files: " + totalLines);

        // Step 6: Shutdown executor
        executor.shutdown();
    }
}
