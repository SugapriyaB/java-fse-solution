import java.util.HashMap;
import java.util.Scanner;

public class StudentMapDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> studentMap = new HashMap<>();
        
        System.out.println("Student ID-Name Mapping System");
        System.out.println("--------------------------\n");
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Add new student");
            System.out.println("2. Look up student");
            System.out.println("3. Display all students");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice (1-4): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice == 4) {
                    break;
                }
                
                switch (choice) {
                    case 1:
                        addStudent(scanner, studentMap);
                        break;
                    case 2:
                        lookupStudent(scanner, studentMap);
                        break;
                    case 3:
                        displayAllStudents(studentMap);
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
        
        scanner.close();
        System.out.println("\nThank you for using the Student ID-Name Mapping System!");
    }
    
    private static void addStudent(Scanner scanner, HashMap<Integer, String> studentMap) {
        try {
            System.out.print("\nEnter student ID (numeric): ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            if (studentMap.containsKey(id)) {
                System.out.println("Error: Student ID " + id + " already exists!");
                return;
            }
            
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                System.out.println("Error: Name cannot be empty!");
                return;
            }
            
            studentMap.put(id, name);
            System.out.println("Successfully added: ID " + id + " - " + name);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric ID!");
        }
    }
    
    private static void lookupStudent(Scanner scanner, HashMap<Integer, String> studentMap) {
        try {
            System.out.print("\nEnter student ID to look up: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            String name = studentMap.get(id);
            if (name != null) {
                System.out.println("Found: ID " + id + " - " + name);
            } else {
                System.out.println("No student found with ID: " + id);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric ID!");
        }
    }
    
    private static void displayAllStudents(HashMap<Integer, String> studentMap) {
        if (studentMap.isEmpty()) {
            System.out.println("\nNo students in the system.");
            return;
        }
        
        System.out.println("\nAll Students:");
        System.out.println("-------------");
        for (var entry : studentMap.entrySet()) {
            System.out.println("ID " + entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Total students: " + studentMap.size());
    }
} 