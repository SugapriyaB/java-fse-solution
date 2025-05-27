public class Car {
    // Attributes (instance variables)
    private String make;
    private String model;
    private int year;
    private double mileage;
    
    // Constructor with all parameters
    public Car(String make, String model, int year, double mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
    }
    
    // Constructor with basic parameters
    public Car(String make, String model, int year) {
        this(make, model, year, 0.0); // Call the full constructor with default mileage
    }
    
    // Getters
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getMileage() {
        return mileage;
    }
    
    // Setters
    public void setMileage(double mileage) {
        if (mileage >= 0) {
            this.mileage = mileage;
        }
    }
    
    // Method to display car details
    public void displayDetails() {
        System.out.println("\nCar Details:");
        System.out.println("------------");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.printf("Mileage: %.1f miles%n", mileage);
    }
    
    // Method to check if car is antique (over 25 years old)
    public boolean isAntique() {
        int currentYear = java.time.Year.now().getValue();
        return (currentYear - year) > 25;
    }
    
    // Override toString method for string representation
    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
} 