public class Animal {
    // Protected attributes accessible by subclasses
    protected String name;
    protected int age;
    protected String species;
    
    // Constructor
    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }
    
    // Default constructor
    public Animal() {
        this("Unknown", 0, "Unknown");
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getSpecies() {
        return species;
    }
    
    // Method to make sound
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
    
    // Method to eat
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    // Method to sleep
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // Display animal details
    public void displayDetails() {
        System.out.println("\nAnimal Details:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
        System.out.println("Species: " + species);
    }
    
    // Override toString method
    @Override
    public String toString() {
        return species + " named " + name + " (" + age + " years old)";
    }
} 