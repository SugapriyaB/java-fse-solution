import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Define the Person record
record Person(String name, int age) {
    // Compact constructor for validation
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        name = name.trim(); // Normalize the name
    }
}

public class RecordDemo {
    public static void main(String[] args) {
        System.out.println("Java Record Demonstration");
        System.out.println("----------------------\n");
        
        try {
            // Create a list of Person records
            List<Person> people = new ArrayList<>();
            people.add(new Person("John Smith", 25));
            people.add(new Person("Alice Johnson", 30));
            people.add(new Person("Bob Wilson", 17));
            people.add(new Person("Carol Brown", 42));
            people.add(new Person("David Miller", 19));
            
            // Display all people
            System.out.println("All People:");
            displayPeople(people);
            
            // Filter adults (age >= 18) using streams
            List<Person> adults = people.stream()
                .filter(person -> person.age() >= 18)
                .collect(Collectors.toList());
            
            System.out.println("\nAdults (age >= 18):");
            displayPeople(adults);
            
            // Find average age using streams
            double averageAge = people.stream()
                .mapToInt(Person::age)
                .average()
                .orElse(0.0);
            
            System.out.printf("\nAverage age: %.1f years%n", averageAge);
            
            // Find youngest and oldest using streams
            Person youngest = people.stream()
                .min((p1, p2) -> p1.age() - p2.age())
                .orElse(null);
            
            Person oldest = people.stream()
                .max((p1, p2) -> p1.age() - p2.age())
                .orElse(null);
            
            System.out.println("\nAge Range:");
            System.out.println("Youngest: " + youngest);
            System.out.println("Oldest: " + oldest);
            
            // Demonstrate record features
            Person john = new Person("John Smith", 25);
            System.out.println("\nRecord Features Demo:");
            System.out.println("toString(): " + john);
            System.out.println("name(): " + john.name());
            System.out.println("age(): " + john.age());
            System.out.println("equals(): " + john.equals(new Person("John Smith", 25)));
            
            // Demonstrate validation
            System.out.println("\nTrying to create invalid records:");
            try {
                new Person("", 20); // Empty name
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            }
            
            try {
                new Person("Test", -1); // Negative age
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    private static void displayPeople(List<Person> people) {
        if (people.isEmpty()) {
            System.out.println("No people to display");
            return;
        }
        
        people.forEach(person -> 
            System.out.printf("- %s (age: %d)%n", 
                person.name(), person.age()));
    }
} 