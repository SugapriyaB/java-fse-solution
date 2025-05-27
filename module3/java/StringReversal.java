import java.util.Scanner;

public class StringReversal {
    // Method to reverse string using loop
    public static String reverseWithLoop(String str) {
        char[] characters = str.toCharArray();
        String reversed = "";
        for (int i = characters.length - 1; i >= 0; i--) {
            reversed += characters[i];
        }
        return reversed;
    }
    
    // Method to reverse string using StringBuilder
    public static String reverseWithStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    // Method to reverse string using char array (more efficient than first method)
    public static String reverseWithCharArray(String str) {
        char[] characters = str.toCharArray();
        int left = 0;
        int right = characters.length - 1;
        
        while (left < right) {
            // Swap characters
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(characters);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Welcome message
        System.out.println("String Reversal Program");
        System.out.println("---------------------\n");
        
        // Get input from user
        System.out.print("Enter a string to reverse: ");
        String input = scanner.nextLine();
        
        // Check if string is empty
        if (input.trim().isEmpty()) {
            System.out.println("Error: Please enter a non-empty string!");
            scanner.close();
            return;
        }
        
        // Reverse using different methods
        String reversedLoop = reverseWithLoop(input);
        String reversedBuilder = reverseWithStringBuilder(input);
        String reversedCharArray = reverseWithCharArray(input);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.println("Original string: " + input);
        System.out.println("Reversed (loop): " + reversedLoop);
        System.out.println("Reversed (StringBuilder): " + reversedBuilder);
        System.out.println("Reversed (char array): " + reversedCharArray);
        
        // Additional information
        System.out.println("\nString Statistics:");
        System.out.println("• Length: " + input.length() + " characters");
        System.out.println("• First character: '" + (input.length() > 0 ? input.charAt(0) : "") + "'");
        System.out.println("• Last character: '" + (input.length() > 0 ? input.charAt(input.length()-1) : "") + "'");
        
        // Check if palindrome
        boolean isPalindrome = input.equalsIgnoreCase(reversedBuilder);
        System.out.println("• Is palindrome: " + (isPalindrome ? "Yes" : "No"));
        
        // Display comparison of methods
        System.out.println("\nMethod Comparison:");
        System.out.println("• Loop method: Simple but less efficient for large strings");
        System.out.println("• StringBuilder: Most efficient for single reversal");
        System.out.println("• Char array: Efficient and memory-friendly");
        
        scanner.close();
    }
} 