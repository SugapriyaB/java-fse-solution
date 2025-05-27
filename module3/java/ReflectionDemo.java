import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Modifier;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // Load the BytecodeDemo class using reflection
            Class<?> dynamicClass = Class.forName("BytecodeDemo");
            
            // Print class information
            System.out.println("Class Name: " + dynamicClass.getName());
            System.out.println("\nDeclared Methods:");
            
            // Get and print information about all declared methods
            Method[] methods = dynamicClass.getDeclaredMethods();
            for (Method method : methods) {
                // Print method modifiers (public, private, etc.)
                System.out.println("\nMethod Name: " + method.getName());
                System.out.println("Return Type: " + method.getReturnType().getSimpleName());
                System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));
                
                // Print parameter information
                Parameter[] parameters = method.getParameters();
                System.out.println("Parameters:");
                if (parameters.length == 0) {
                    System.out.println("  None");
                } else {
                    for (Parameter param : parameters) {
                        System.out.println("  " + param.getType().getSimpleName() + " " + param.getName());
                    }
                }
            }
            
            // Create an instance of BytecodeDemo using reflection
            Object instance = dynamicClass.getDeclaredConstructor().newInstance();
            
            // Find and invoke the calculateFactorial method
            Method factorialMethod = dynamicClass.getMethod("calculateFactorial", int.class);
            System.out.println("\nInvoking calculateFactorial(5) using reflection:");
            Object result = factorialMethod.invoke(instance, 5);
            System.out.println("Result: " + result);
            
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 