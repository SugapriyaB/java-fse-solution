import java.util.Scanner;

/**
 * A simple calculator program that performs basic arithmetic operations
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get first number
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        // Get second number
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        // Display operation choices
        System.out.println("\nChoose an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.print("Enter your choice (1-4): ");
        
        int choice = scanner.nextInt();
        double result = 0;
        String operation = "";
        
        // Perform calculation based on user's choice
        switch (choice) {
            case 1:
                result = num1 + num2;
                operation = "+";
                break;
            case 2:
                result = num1 - num2;
                operation = "-";
                break;
            case 3:
                result = num1 * num2;
                operation = "*";
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                    operation = "/";
                } else {
                    System.out.println("Error: Cannot divide by zero!");
                    scanner.close();
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }
        
        // Display the result
        System.out.printf("\n%.2f %s %.2f = %.2f\n", 
            num1, operation, num2, result);
        
        scanner.close();
    }
} 