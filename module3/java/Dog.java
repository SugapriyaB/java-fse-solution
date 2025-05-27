public class Dog extends Animal {
    // Additional attributes specific to Dog
    private String breed;
    private boolean isVaccinated;
    
    // Constructor
    public Dog(String name, int age, String breed, boolean isVaccinated) {
        super(name, age, "Dog"); // Call parent constructor with species as "Dog"
        this.breed = breed;
        this.isVaccinated = isVaccinated;
    }
    
    // Default constructor
    public Dog() {
        this("Unknown", 0, "Mixed Breed", false);
    }
    
    // Getter for breed
    public String getBreed() {
        return breed;
    }
    
    // Getter for vaccination status
    public boolean isVaccinated() {
        return isVaccinated;
    }
    
    // Setter for vaccination status
    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }
    
    // Override makeSound method
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
    
    // Dog-specific method to fetch
    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
    
    // Override displayDetails to include dog-specific information
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call parent's displayDetails method
        System.out.println("Breed: " + breed);
        System.out.println("Vaccinated: " + (isVaccinated ? "Yes" : "No"));
    }
    
    // Override toString method
    @Override
    public String toString() {
        return breed + " Dog named " + name + " (" + age + " years old)";
    }
} 