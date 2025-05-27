import java.util.Scanner;

public class DivisionCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Division Calculator with Exception Handling");
        System.out.println("----------------------------------------\n");
        
        try {
            // Get first number
            System.out.print("Enter the first number (dividend): ");
            int dividend = scanner.nextInt();
            
            // Get second number
            System.out.print("Enter the second number (divisor): ");
            int divisor = scanner.nextInt();
            
            // Perform division
            int result = performDivision(dividend, divisor);
            
            // Display result
            System.out.println("\nResult: " + dividend + " ÷ " + divisor + " = " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("\nError: Cannot divide by zero!");
            System.out.println("Please enter a non-zero divisor.");
        } catch (Exception e) {
            System.out.println("\nError: Invalid input!");
            System.out.println("Please enter valid integer numbers.");
        } finally {
            // Close the scanner
            scanner.close();
            System.out.println("\nThank you for using the Division Calculator!");
        }
    }
    
    // Method to perform division
    private static int performDivision(int dividend, int divisor) throws ArithmeticException {
        return dividend / divisor;
    }
} 