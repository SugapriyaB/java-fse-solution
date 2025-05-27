public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("Thread Demonstration");
        System.out.println("------------------\n");
        
        // Create thread using Thread class extension
        MessageThread thread1 = new MessageThread("Thread-A");
        
        // Create thread using Runnable interface
        MessageRunnable runnable = new MessageRunnable("Thread-B");
        Thread thread2 = new Thread(runnable);
        
        System.out.println("Starting threads...\n");
        
        // Start both threads
        thread1.start();
        thread2.start();
        
        try {
            // Wait for both threads to complete
            thread1.join();
            thread2.join();
            System.out.println("\nBoth threads have completed!");
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted!");
        }
    }
}

// First approach: Extending Thread class
class MessageThread extends Thread {
    private final String message;
    private static final int REPEAT_COUNT = 5;
    private static final int SLEEP_MS = 1000;
    
    public MessageThread(String name) {
        super(name);
        this.message = name;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= REPEAT_COUNT; i++) {
                System.out.printf("%s: Message %d of %d%n", 
                    message, i, REPEAT_COUNT);
                Thread.sleep(SLEEP_MS);
            }
        } catch (InterruptedException e) {
            System.out.println(message + " was interrupted!");
        }
    }
}

// Second approach: Implementing Runnable interface
class MessageRunnable implements Runnable {
    private final String message;
    private static final int REPEAT_COUNT = 5;
    private static final int SLEEP_MS = 1000;
    
    public MessageRunnable(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= REPEAT_COUNT; i++) {
                System.out.printf("%s: Message %d of %d%n", 
                    message, i, REPEAT_COUNT);
                Thread.sleep(SLEEP_MS);
            }
        } catch (InterruptedException e) {
            System.out.println(message + " was interrupted!");
        }
    }
} 