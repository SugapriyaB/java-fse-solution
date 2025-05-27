import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for input
        System.out.print("Enter a number to generate its multiplication table: ");
        
        // Read the number from user
        int number = scanner.nextInt();
        
        // Print header
        System.out.println("\nMultiplication Table for " + number + ":");
        System.out.println("------------------------");
        
        // Generate multiplication table using for loop
        for (int i = 1; i <= 10; i++) {
            int result = number * i;
            System.out.printf("%d × %d = %d%n", number, i, result);
        }
        
        // Close the scanner
        scanner.close();
    }
} 