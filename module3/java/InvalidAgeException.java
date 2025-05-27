public class InvalidAgeException extends Exception {
    public InvalidAgeException() {
        super("Age must be 18 or older!");
    }
    
    public InvalidAgeException(String message) {
        super(message);
    }
    
    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
} 