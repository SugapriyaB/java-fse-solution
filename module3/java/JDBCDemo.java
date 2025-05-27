import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCDemo {
    // Database path - will create in current directory
    private static final String DB_URL = "jdbc:sqlite:module3/java/students.db";
    
    public static void main(String[] args) {
        System.out.println("JDBC Database Demonstration");
        System.out.println("-----------------------\n");
        
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Create tables and insert sample data
            setupDatabase();
            
            // Perform CRUD operations
            System.out.println("Performing database operations:");
            System.out.println("-----------------------------");
            
            // Insert a new student
            insertStudent("Alice Johnson", "Computer Science", 3.8);
            insertStudent("Bob Wilson", "Mathematics", 3.9);
            insertStudent("Carol Brown", "Physics", 3.7);
            
            // Query and display all students
            System.out.println("\nAll Students:");
            displayAllStudents();
            
            // Update a student's GPA
            updateStudentGPA("Alice Johnson", 3.9);
            System.out.println("\nAfter updating Alice's GPA:");
            displayAllStudents();
            
            // Delete a student
            deleteStudent("Bob Wilson");
            System.out.println("\nAfter deleting Bob:");
            displayAllStudents();
            
            // Search by department
            System.out.println("\nComputer Science students:");
            searchByDepartment("Computer Science");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: SQLite JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error occurred!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }
    
    private static void setupDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            // Create students table
            String createTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    department TEXT NOT NULL,
                    gpa REAL NOT NULL
                )
                """;
            stmt.execute(createTable);
            
            // Clear existing data
            stmt.execute("DELETE FROM students");
        }
    }
    
    private static void insertStudent(String name, String department, double gpa) throws SQLException {
        String sql = "INSERT INTO students (name, department, gpa) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, gpa);
            pstmt.executeUpdate();
            
            System.out.printf("Inserted student: %s (%s)%n", name, department);
        }
    }
    
    private static void displayAllStudents() throws SQLException {
        String sql = "SELECT * FROM students ORDER BY name";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.printf("- %s (%s) - GPA: %.2f%n",
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("gpa"));
            }
        }
    }
    
    private static void updateStudentGPA(String name, double newGPA) throws SQLException {
        String sql = "UPDATE students SET gpa = ? WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newGPA);
            pstmt.setString(2, name);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.printf("Updated GPA for %s to %.2f%n", name, newGPA);
            }
        }
    }
    
    private static void deleteStudent(String name) throws SQLException {
        String sql = "DELETE FROM students WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.printf("Deleted student: %s%n", name);
            }
        }
    }
    
    private static void searchByDepartment(String department) throws SQLException {
        String sql = "SELECT * FROM students WHERE department = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, department);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.printf("- %s - GPA: %.2f%n",
                    rs.getString("name"),
                    rs.getDouble("gpa"));
            }
        }
    }
} 