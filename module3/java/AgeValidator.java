import java.util.Scanner;

public class AgeValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Age Validation with Custom Exception");
        System.out.println("----------------------------------\n");
        
        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            
            validateAge(age);
            
            System.out.println("\nValidation successful! You are " + age + " years old.");
            System.out.println("You are eligible to proceed.");
            
        } catch (InvalidAgeException e) {
            System.out.println("\nError: " + e.getMessage());
            System.out.println("Please try again when you are of legal age.");
        } catch (Exception e) {
            System.out.println("\nError: Invalid input!");
            System.out.println("Please enter a valid number for age.");
        } finally {
            scanner.close();
            System.out.println("\nThank you for using the Age Validator!");
        }
    }
    
    // Method to validate age
    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative!");
        } else if (age < 18) {
            throw new InvalidAgeException("You must be at least 18 years old! (Current age: " + age + ")");
        }
    }
} 