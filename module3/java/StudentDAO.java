import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String DB_URL = "jdbc:sqlite:module3/java/students.db";
    
    // Initialize the database
    public void initializeDatabase() throws SQLException {
        String createTable = """
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                department TEXT NOT NULL,
                gpa REAL NOT NULL
            )
            """;
            
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
        }
    }
    
    // Insert a new student
    public Student insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, department, gpa) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setDouble(3, student.getGpa());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }
            
            return student;
        }
    }
    
    // Update an existing student
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, department = ?, gpa = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setDouble(3, student.getGpa());
            pstmt.setInt(4, student.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Update student's GPA
    public boolean updateStudentGPA(int studentId, double newGPA) throws SQLException {
        String sql = "UPDATE students SET gpa = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, newGPA);
            pstmt.setInt(2, studentId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Get student by ID
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("gpa")
                    );
                }
                return null;
            }
        }
    }
    
    // Get all students
    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students ORDER BY name";
        List<Student> students = new ArrayList<>();
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("gpa")
                ));
            }
        }
        
        return students;
    }
    
    // Delete a student
    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Helper method to get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
} 