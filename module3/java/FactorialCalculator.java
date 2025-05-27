import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Welcome message
        System.out.println("Factorial Calculator");
        System.out.println("-------------------");
        
        // Prompt user for input
        System.out.print("Enter a non-negative integer (0-20): ");
        
        // Input validation
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer!");
            scanner.next(); // Clear invalid input
            System.out.print("Enter a non-negative integer (0-20): ");
        }
        
        int number = scanner.nextInt();
        
        // Validate if number is non-negative
        if (number < 0) {
            System.out.println("Error: Factorial is not defined for negative numbers!");
        } else if (number > 20) {
            System.out.println("Error: Number too large! Please enter a number between 0 and 20.");
        } else {
            // Calculate factorial using long to handle larger results
            long factorial = 1;
            
            // Use for loop to calculate factorial
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            
            // Display the calculation process
            System.out.println("\nCalculation Process:");
            System.out.print(number + "! = ");
            
            // Show the multiplication series
            if (number == 0 || number == 1) {
                System.out.print("1");
            } else {
                for (int i = number; i >= 1; i--) {
                    System.out.print(i);
                    if (i > 1) {
                        System.out.print(" × ");
                    }
                }
            }
            
            // Display the final result
            System.out.println("\nResult: " + number + "! = " + factorial);
            
            // Additional information
            if (number <= 5) {
                System.out.println("\nFactorial Facts:");
                System.out.println("• 0! is defined as 1");
                System.out.println("• 1! is also 1");
                System.out.println("• Factorial grows very quickly!");
            }
        }
        
        // Close scanner
        scanner.close();
    }
} 