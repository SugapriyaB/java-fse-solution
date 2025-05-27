import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileWriterDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("File Writing Demonstration");
        System.out.println("-----------------------\n");
        
        try {
            // Get user input
            System.out.println("Enter the text you want to write to the file:");
            System.out.println("(Type your message and press Enter when done)");
            String userInput = scanner.nextLine();
            
            // Create file object
            File file = new File("module3/java/output.txt");
            
            // Create parent directories if they don't exist
            file.getParentFile().mkdirs();
            
            // Write to file using BufferedWriter with UTF-8 encoding
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file), StandardCharsets.UTF_8))) {
                
                writer.write(userInput);
                writer.newLine(); // Add a newline at the end
                
                // Flush to ensure all data is written
                writer.flush();
                
                // Confirm successful write
                System.out.println("\nSuccess! The text has been written to: " + file.getAbsolutePath());
                System.out.println("File size: " + file.length() + " bytes");
            }
            
        } catch (SecurityException e) {
            System.out.println("\nError: No permission to create or write to the file!");
            System.out.println("Details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError occurred while writing to file!");
            System.out.println("Details: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nThank you for using the File Writer!");
        }
    }
} 