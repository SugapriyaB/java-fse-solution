import java.util.ArrayList;
import java.util.Scanner;

public class StudentListDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        
        System.out.println("Student List Management");
        System.out.println("---------------------\n");
        
        boolean continueAdding = true;
        
        while (continueAdding) {
            System.out.print("Enter student name (or type 'exit' to finish): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit")) {
                continueAdding = false;
            } else if (input.isEmpty()) {
                System.out.println("Error: Name cannot be empty! Please try again.");
            } else {
                studentNames.add(input);
                System.out.println("Added: " + input);
            }
        }
        
        System.out.println("\nStudent List Summary");
        System.out.println("-----------------");
        
        if (studentNames.isEmpty()) {
            System.out.println("No students in the list.");
        } else {
            System.out.println("Total students: " + studentNames.size());
            System.out.println("\nList of students:");
            for (int i = 0; i < studentNames.size(); i++) {
                System.out.println((i + 1) + ". " + studentNames.get(i));
            }
        }
        
        scanner.close();
        System.out.println("\nThank you for using Student List Management!");
    }
} 