public class Student {
    private int id;
    private String name;
    private String department;
    private double gpa;
    
    // Constructor for new students (without ID)
    public Student(String name, String department, double gpa) {
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }
    
    // Constructor for existing students (with ID)
    public Student(int id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    
    @Override
    public String toString() {
        return String.format("Student[id=%d, name='%s', department='%s', gpa=%.2f]",
            id, name, department, gpa);
    }
} 