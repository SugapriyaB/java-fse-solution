import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCallableDemo {
    // Callable task that calculates square of a number
    static class SquareCalculator implements Callable<Integer> {
        private final int number;

        public SquareCalculator(int number) {
            this.number = number;
        }

        @Override
        public Integer call() {
            System.out.println("Calculating square of " + number + 
                             " in thread: " + Thread.currentThread().getName());
            try {
                // Simulate some processing time
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return number * number;
        }
    }

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("Created thread pool with 3 threads\n");

        // List to store Future objects
        List<Future<Integer>> futures = new ArrayList<>();

        // Submit 5 tasks for calculation
        for (int i = 1; i <= 5; i++) {
            Future<Integer> future = executor.submit(new SquareCalculator(i));
            futures.add(future);
        }

        // Collect and print results
        System.out.println("Collecting results:\n");
        for (int i = 0; i < futures.size(); i++) {
            try {
                Integer result = futures.get(i).get();
                System.out.printf("Square of %d = %d%n", (i + 1), result);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error getting result: " + e.getMessage());
            }
        }

        // Shutdown the executor
        executor.shutdown();
        System.out.println("\nAll tasks completed!");
    }
} 