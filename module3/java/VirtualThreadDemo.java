import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadDemo {
    private static final int THREAD_COUNT = 100_000;
    private static final AtomicInteger messageCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Thread Comparison Demo...\n");
        
        // Test Virtual Threads
        Instant virtualStart = Instant.now();
        testVirtualThreads();
        Duration virtualDuration = Duration.between(virtualStart, Instant.now());
        
        // Reset counter and wait a bit
        messageCounter.set(0);
        Thread.sleep(1000);
        
        // Test Platform Threads
        Instant platformStart = Instant.now();
        testPlatformThreads();
        Duration platformDuration = Duration.between(platformStart, Instant.now());
        
        // Print final results
        System.out.println("\nPerformance Results:");
        System.out.println("Virtual Threads time: " + virtualDuration.toMillis() + "ms");
        System.out.println("Platform Threads time: " + platformDuration.toMillis() + "ms");
        System.out.println("Performance difference: " + 
            String.format("%.2fx", (double)platformDuration.toMillis() / virtualDuration.toMillis()) + 
            " faster with Virtual Threads");
    }

    private static void testVirtualThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        System.out.println("Launching " + THREAD_COUNT + " Virtual Threads...");
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    int messageNum = messageCounter.incrementAndGet();
                    System.out.println("Virtual Thread #" + messageNum + " says hello!");
                    Thread.sleep(10); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        System.out.println("All virtual threads completed!");
    }

    private static void testPlatformThreads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        System.out.println("Launching " + THREAD_COUNT + " Platform Threads...");
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread platformThread = new Thread(() -> {
                try {
                    int messageNum = messageCounter.incrementAndGet();
                    System.out.println("Platform Thread #" + messageNum + " says hello!");
                    Thread.sleep(10); // Simulate same work as virtual threads
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
            platformThread.start();
        }
        latch.await();
        System.out.println("All platform threads completed!");
    }
} 