import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Create Scanner for user input and Random for number generation
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Generate random number between 1 and 100
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfTries = 0;
        int userGuess;
        boolean hasGuessedCorrectly = false;
        
        // Welcome message
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've picked a number between 1 and 100.");
        System.out.println("Try to guess it!\n");
        
        // Game loop
        while (!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            
            // Input validation
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next(); // Clear invalid input
                continue;
            }
            
            userGuess = scanner.nextInt();
            numberOfTries++;
            
            // Check if guess is valid
            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Please enter a number between 1 and 100!");
                continue;
            }
            
            // Provide feedback
            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try a higher number.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try a lower number.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("\nCongratulations! You've guessed the number!");
                System.out.println("The number was: " + numberToGuess);
                System.out.println("Number of tries: " + numberOfTries);
                
                // Provide performance feedback
                if (numberOfTries <= 5) {
                    System.out.println("Excellent! You're a pro!");
                } else if (numberOfTries <= 10) {
                    System.out.println("Good job! That's a solid performance!");
                } else {
                    System.out.println("Not bad! Keep practicing to improve!");
                }
            }
            
            System.out.println(); // Add blank line for readability
        }
        
        // Close scanner
        scanner.close();
    }
} 