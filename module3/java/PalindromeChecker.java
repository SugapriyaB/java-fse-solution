import java.util.Scanner;

public class PalindromeChecker {
    // Method to clean the string (remove non-alphanumeric and convert to lowercase)
    public static String cleanString(String str) {
        // Remove all non-alphanumeric characters and convert to lowercase
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
    
    // Method to check if string is palindrome
    public static boolean isPalindrome(String str) {
        // Clean the string first
        String cleanStr = cleanString(str);
        
        // Empty string or single character is a palindrome
        if (cleanStr.length() <= 1) {
            return true;
        }
        
        // Check characters from both ends moving towards center
        int left = 0;
        int right = cleanStr.length() - 1;
        
        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    // Method to get reversed string (for display purposes)
    public static String getReversed(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Welcome message
        System.out.println("Palindrome Checker");
        System.out.println("-----------------\n");
        System.out.println("A palindrome reads the same forwards and backwards.");
        System.out.println("Examples: 'radar', 'A man, a plan, a canal: Panama'\n");
        
        // Get input from user
        System.out.print("Enter a string to check: ");
        String input = scanner.nextLine();
        
        // Check if string is empty
        if (input.trim().isEmpty()) {
            System.out.println("Error: Please enter a non-empty string!");
            scanner.close();
            return;
        }
        
        // Clean the string and check if it's a palindrome
        String cleanedString = cleanString(input);
        boolean isPalindrome = isPalindrome(input);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.println("Original string: " + input);
        System.out.println("Cleaned string: " + cleanedString);
        System.out.println("Reversed cleaned string: " + getReversed(cleanedString));
        System.out.println("Is palindrome: " + (isPalindrome ? "Yes" : "No"));
        
        // Additional analysis
        System.out.println("\nAnalysis:");
        System.out.println("• Original length: " + input.length() + " characters");
        System.out.println("• Cleaned length: " + cleanedString.length() + " characters");
        System.out.println("• Characters removed: " + (input.length() - cleanedString.length()));
        
        // Examples if not a palindrome
        if (!isPalindrome && cleanedString.length() > 0) {
            System.out.println("\nExample palindromes:");
            System.out.println("• Simple: radar, level, deed");
            System.out.println("• Phrases: never odd or even");
            System.out.println("• With punctuation: A man, a plan, a canal: Panama!");
        }
        
        scanner.close();
    }
} 