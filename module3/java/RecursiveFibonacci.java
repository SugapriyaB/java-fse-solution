import java.util.Scanner;

public class RecursiveFibonacci {
    // Recursive method to calculate Fibonacci number
    public static long fibonacci(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        // Recursive case: Fib(n) = Fib(n-1) + Fib(n-2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Iterative method to display Fibonacci sequence up to nth number
    public static void displaySequence(int n) {
        System.out.print("Fibonacci Sequence up to " + n + ": ");
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacci(i));
            if (i < n) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Welcome message
        System.out.println("Recursive Fibonacci Calculator");
        System.out.println("--------------------------");
        
        // Input with validation
        int n;
        do {
            System.out.print("\nEnter a positive integer (0-40): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer!");
                scanner.next();
                System.out.print("Enter a positive integer (0-40): ");
            }
            n = scanner.nextInt();
            
            if (n < 0) {
                System.out.println("Please enter a non-negative number!");
            } else if (n > 40) {
                System.out.println("Number too large! Please enter a number <= 40 to avoid long calculation times.");
            }
        } while (n < 0 || n > 40);
        
        // Calculate and display result
        System.out.println("\nCalculating Fibonacci number...");
        long startTime = System.currentTimeMillis();
        long result = fibonacci(n);
        long endTime = System.currentTimeMillis();
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.println("The " + n + "th Fibonacci number is: " + result);
        
        // Display the sequence up to n if n is small
        if (n <= 10) {
            System.out.println("\nComplete sequence:");
            displaySequence(n);
        }
        
        // Display calculation time
        System.out.println("\nCalculation took " + (endTime - startTime) + " milliseconds");
        
        // Display Fibonacci facts
        System.out.println("\nFibonacci Facts:");
        System.out.println("• The sequence starts with 0, 1");
        System.out.println("• Each number is the sum of the two preceding ones");
        System.out.println("• The sequence grows exponentially");
        System.out.println("• Fibonacci numbers appear frequently in nature");
        
        // Warning about recursive implementation
        if (n > 35) {
            System.out.println("\nNote: For large numbers, an iterative implementation");
            System.out.println("would be more efficient than this recursive approach.");
        }
        
        scanner.close();
    }
} 