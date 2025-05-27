import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleLambdaSort {
    public static void main(String[] args) {
        System.out.println("Simple Lambda Sort Demonstration");
        System.out.println("------------------------------\n");
        
        // Create a list of strings
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("David");
        names.add("Carol");
        
        // Display original list
        System.out.println("Original List:");
        displayList(names);
        
        // Sort using lambda expression
        Collections.sort(names, (name1, name2) -> name1.compareTo(name2));
        
        // Display sorted list
        System.out.println("\nSorted List (using lambda):");
        displayList(names);
        
        // Reverse sort using lambda expression
        Collections.sort(names, (name1, name2) -> name2.compareTo(name1));
        
        // Display reverse sorted list
        System.out.println("\nReverse Sorted List (using lambda):");
        displayList(names);
    }
    
    private static void displayList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
} 