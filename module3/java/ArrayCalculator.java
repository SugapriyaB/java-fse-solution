import java.util.Scanner;

public class ArrayCalculator {
    // Method to calculate sum of array elements
    public static double calculateSum(double[] array) {
        double sum = 0;
        for (double element : array) {
            sum += element;
        }
        return sum;
    }
    
    // Method to calculate average of array elements
    public static double calculateAverage(double[] array) {
        if (array.length == 0) {
            return 0;
        }
        return calculateSum(array) / array.length;
    }
    
    // Method to find maximum element
    public static double findMax(double[] array) {
        if (array.length == 0) {
            return 0;
        }
        double max = array[0];
        for (double element : array) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }
    
    // Method to find minimum element
    public static double findMin(double[] array) {
        if (array.length == 0) {
            return 0;
        }
        double min = array[0];
        for (double element : array) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Welcome message
        System.out.println("Array Calculator");
        System.out.println("---------------\n");
        
        // Get array size with validation
        int size;
        do {
            System.out.print("Enter the number of elements (1-100): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next();
                System.out.print("Enter the number of elements (1-100): ");
            }
            size = scanner.nextInt();
            
            if (size <= 0 || size > 100) {
                System.out.println("Please enter a number between 1 and 100!");
            }
        } while (size <= 0 || size > 100);
        
        // Create and populate the array
        double[] numbers = new double[size];
        System.out.println("\nEnter " + size + " numbers:");
        
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid number!");
                scanner.next();
                System.out.print("Element " + (i + 1) + ": ");
            }
            numbers[i] = scanner.nextDouble();
        }
        
        // Calculate results
        double sum = calculateSum(numbers);
        double average = calculateAverage(numbers);
        double max = findMax(numbers);
        double min = findMin(numbers);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.printf("Sum: %.2f%n", sum);
        System.out.printf("Average: %.2f%n", average);
        System.out.printf("Maximum: %.2f%n", max);
        System.out.printf("Minimum: %.2f%n", min);
        
        // Display array elements
        System.out.println("\nArray Elements:");
        System.out.print("[");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("%.2f", numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        // Additional statistics
        System.out.println("\nAdditional Information:");
        System.out.println("• Number of elements: " + size);
        System.out.println("• Range (Max - Min): " + String.format("%.2f", (max - min)));
        
        scanner.close();
    }
} 