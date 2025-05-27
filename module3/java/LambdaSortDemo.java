import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaSortDemo {
    public static void main(String[] args) {
        System.out.println("Lambda Expression Sorting Demonstration");
        System.out.println("------------------------------------\n");
        
        // Create and initialize the list
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("orange");
        fruits.add("Kiwi");
        fruits.add("blueberry");
        fruits.add("Mango");
        
        // Display original list
        System.out.println("Original List:");
        displayList(fruits);
        
        // Sort case-sensitive (natural order)
        Collections.sort(fruits);
        System.out.println("\nNatural Sort (case-sensitive):");
        displayList(fruits);
        
        // Sort case-insensitive using lambda
        Collections.sort(fruits, (str1, str2) -> str1.toLowerCase().compareTo(str2.toLowerCase()));
        System.out.println("\nCase-insensitive Sort:");
        displayList(fruits);
        
        // Sort by length using lambda
        Collections.sort(fruits, (str1, str2) -> str1.length() - str2.length());
        System.out.println("\nSort by Length:");
        displayList(fruits);
        
        // Sort by length, then alphabetically for same length
        Collections.sort(fruits, (str1, str2) -> {
            int lengthCompare = str1.length() - str2.length();
            return lengthCompare != 0 ? lengthCompare 
                                    : str1.compareToIgnoreCase(str2);
        });
        System.out.println("\nSort by Length then Alphabetically:");
        displayList(fruits);
    }
    
    private static void displayList(List<String> list) {
        System.out.println("------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s (length: %d)%n", 
                i + 1, list.get(i), list.get(i).length());
        }
        System.out.println("------------------------");
    }
} 