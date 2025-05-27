public class MethodOverloadingDemo {
    // Method to add two integers
    public static int add(int num1, int num2) {
        return num1 + num2;
    }
    
    // Method to add two doubles
    public static double add(double num1, double num2) {
        return num1 + num2;
    }
    
    // Method to add three integers
    public static int add(int num1, int num2, int num3) {
        return num1 + num2 + num3;
    }
    
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Method Overloading Demonstration");
        System.out.println("------------------------------\n");
        
        // Test case 1: Adding two integers
        int sum1 = add(5, 10);
        System.out.println("1. Adding two integers:");
        System.out.println("   add(5, 10) = " + sum1);
        System.out.println("   Method used: add(int num1, int num2)\n");
        
        // Test case 2: Adding two doubles
        double sum2 = add(3.5, 2.7);
        System.out.println("2. Adding two doubles:");
        System.out.println("   add(3.5, 2.7) = " + sum2);
        System.out.println("   Method used: add(double num1, double num2)\n");
        
        // Test case 3: Adding three integers
        int sum3 = add(10, 20, 30);
        System.out.println("3. Adding three integers:");
        System.out.println("   add(10, 20, 30) = " + sum3);
        System.out.println("   Method used: add(int num1, int num2, int num3)\n");
        
        // Demonstrating automatic type conversion
        System.out.println("Bonus: Automatic Type Conversion");
        System.out.println("--------------------------------");
        double sum4 = add(5.5, 10); // int 10 is automatically converted to double
        System.out.println("add(5.5, 10) = " + sum4);
        System.out.println("Note: Second parameter (10) was automatically converted to double\n");
        
        // Summary of method overloading
        System.out.println("Summary of Method Overloading:");
        System.out.println("1. Methods can have the same name but different parameter types");
        System.out.println("2. Methods can have the same name but different number of parameters");
        System.out.println("3. Java automatically chooses the correct method based on arguments");
        System.out.println("4. Automatic type conversion happens when necessary");
    }
} 