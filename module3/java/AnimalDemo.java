public class AnimalDemo {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Animal Inheritance Demonstration");
        System.out.println("-----------------------------\n");
        
        // Create an Animal instance
        Animal genericAnimal = new Animal("Charlie", 3, "Unknown");
        
        // Create a Dog instance
        Dog dog = new Dog("Max", 5, "Golden Retriever", true);
        
        // Demonstrate inheritance
        System.out.println("Demonstrating Animal Class:");
        System.out.println("-------------------------");
        genericAnimal.displayDetails();
        System.out.print("Sound: ");
        genericAnimal.makeSound();
        genericAnimal.eat();
        genericAnimal.sleep();
        
        System.out.println("\nDemonstrating Dog Class:");
        System.out.println("----------------------");
        dog.displayDetails();
        System.out.print("Sound: ");
        dog.makeSound();
        dog.eat();
        dog.sleep();
        dog.fetch(); // Dog-specific method
        
        // Demonstrate polymorphism
        System.out.println("\nDemonstrating Polymorphism:");
        System.out.println("-------------------------");
        Animal polymorphicDog = new Dog("Buddy", 2, "Labrador", true);
        System.out.println("Using Dog object through Animal reference:");
        polymorphicDog.displayDetails();
        System.out.print("Sound: ");
        polymorphicDog.makeSound(); // Will use Dog's implementation
        
        // Compare toString implementations
        System.out.println("\nComparing toString() implementations:");
        System.out.println("Generic Animal: " + genericAnimal);
        System.out.println("Dog: " + dog);
        System.out.println("Polymorphic Dog: " + polymorphicDog);
    }
} 