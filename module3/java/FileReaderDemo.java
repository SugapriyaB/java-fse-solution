import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReaderDemo {
    public static void main(String[] args) {
        System.out.println("File Reading Demonstration");
        System.out.println("-----------------------\n");
        
        // Create file object
        File file = new File("module3/java/output.txt");
        
        // Check if file exists
        if (!file.exists()) {
            System.out.println("Error: The file " + file.getAbsolutePath() + " does not exist!");
            System.out.println("Please run FileWriterDemo first to create and write to the file.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8))) {
            
            System.out.println("Contents of " + file.getName() + ":");
            System.out.println("--------------------");
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            System.out.println("--------------------");
            System.out.println("File size: " + file.length() + " bytes");
            
        } catch (SecurityException e) {
            System.out.println("\nError: No permission to read the file!");
            System.out.println("Details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError occurred while reading the file!");
            System.out.println("Details: " + e.getMessage());
        } finally {
            System.out.println("\nThank you for using the File Reader!");
        }
    }
} 