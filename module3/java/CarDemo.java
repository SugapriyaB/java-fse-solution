public class CarDemo {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Car Class Demonstration");
        System.out.println("---------------------");
        
        // Create car objects
        Car car1 = new Car("Toyota", "Camry", 2022, 15000.5);
        Car car2 = new Car("Honda", "Civic", 2023);
        Car car3 = new Car("Ford", "Mustang", 1969, 150000.0);
        
        // Display details for each car
        System.out.println("\nDisplaying information for multiple cars:");
        
        // First car
        car1.displayDetails();
        System.out.println("Is antique: " + car1.isAntique());
        
        // Second car
        car2.displayDetails();
        System.out.println("Is antique: " + car2.isAntique());
        
        // Third car
        car3.displayDetails();
        System.out.println("Is antique: " + car3.isAntique());
        
        // Demonstrate setter method
        System.out.println("\nUpdating mileage for the Honda Civic...");
        car2.setMileage(5000.75);
        car2.displayDetails();
        
        // Demonstrate toString method
        System.out.println("\nUsing toString() method:");
        System.out.println("Car 1: " + car1.toString());
        System.out.println("Car 2: " + car2.toString());
        System.out.println("Car 3: " + car3.toString());
        
        // Demonstrate object comparison
        System.out.println("\nCar Information Summary:");
        System.out.println("• Newest car: " + findNewestCar(car1, car2, car3).toString());
        System.out.println("• Highest mileage: " + findHighestMileage(car1, car2, car3).toString());
    }
    
    // Helper method to find the newest car
    private static Car findNewestCar(Car... cars) {
        Car newest = cars[0];
        for (Car car : cars) {
            if (car.getYear() > newest.getYear()) {
                newest = car;
            }
        }
        return newest;
    }
    
    // Helper method to find car with highest mileage
    private static Car findHighestMileage(Car... cars) {
        Car highest = cars[0];
        for (Car car : cars) {
            if (car.getMileage() > highest.getMileage()) {
                highest = car;
            }
        }
        return highest;
    }
} 