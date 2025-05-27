import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for marks
        System.out.print("Enter marks (0-100): ");
        
        // Read marks
        int marks = scanner.nextInt();
        
        // Variable to store the grade
        String grade;
        
        // Input validation
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Please enter marks between 0 and 100.");
            scanner.close();
            return;
        }
        
        // Grade calculation using if-else statements
        if (marks >= 90) {
            grade = "A";
        } else if (marks >= 80) {
            grade = "B";
        } else if (marks >= 70) {
            grade = "C";
        } else if (marks >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        
        // Display results with detailed information
        System.out.println("\nGrade Report");
        System.out.println("-----------");
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        
        // Additional feedback based on grade
        System.out.print("Status: ");
        if (grade.equals("F")) {
            System.out.println("Failed. Need improvement!");
        } else if (grade.equals("A")) {
            System.out.println("Excellent performance!");
        } else {
            System.out.println("Passed!");
        }
        
        // Display grade scale for reference
        System.out.println("\nGrade Scale:");
        System.out.println("A: 90-100");
        System.out.println("B: 80-89");
        System.out.println("C: 70-79");
        System.out.println("D: 60-69");
        System.out.println("F: Below 60");
        
        // Close the scanner
        scanner.close();
    }
} 