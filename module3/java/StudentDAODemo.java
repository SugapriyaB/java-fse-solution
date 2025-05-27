public class StudentDAODemo {
    public static void main(String[] args) {
        System.out.println("Student DAO Demonstration");
        System.out.println("----------------------\n");
        
        StudentDAO studentDAO = new StudentDAO();
        
        try {
            // Initialize database
            studentDAO.initializeDatabase();
            
            // Insert new students
            System.out.println("Inserting new students:");
            Student alice = new Student("Alice Johnson", "Computer Science", 3.8);
            Student bob = new Student("Bob Wilson", "Mathematics", 3.9);
            Student carol = new Student("Carol Brown", "Physics", 3.7);
            
            alice = studentDAO.insertStudent(alice);
            bob = studentDAO.insertStudent(bob);
            carol = studentDAO.insertStudent(carol);
            
            System.out.println("Inserted: " + alice);
            System.out.println("Inserted: " + bob);
            System.out.println("Inserted: " + carol);
            
            // Display all students
            System.out.println("\nAll students:");
            for (Student student : studentDAO.getAllStudents()) {
                System.out.println(student);
            }
            
            // Update a student's GPA
            System.out.println("\nUpdating Alice's GPA to 4.0:");
            if (studentDAO.updateStudentGPA(alice.getId(), 4.0)) {
                Student updatedAlice = studentDAO.getStudentById(alice.getId());
                System.out.println("Updated: " + updatedAlice);
            }
            
            // Update all student information
            System.out.println("\nUpdating Bob's information:");
            bob.setDepartment("Computer Science");
            bob.setGpa(3.95);
            if (studentDAO.updateStudent(bob)) {
                Student updatedBob = studentDAO.getStudentById(bob.getId());
                System.out.println("Updated: " + updatedBob);
            }
            
            // Delete a student
            System.out.println("\nDeleting Carol:");
            if (studentDAO.deleteStudent(carol.getId())) {
                System.out.println("Deleted student with ID: " + carol.getId());
            }
            
            // Display final student list
            System.out.println("\nFinal student list:");
            for (Student student : studentDAO.getAllStudents()) {
                System.out.println(student);
            }
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 