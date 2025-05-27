import java.util.ArrayList;
import java.util.List;

public class PatternMatchingDemo {
    public static void main(String[] args) {
        System.out.println("Pattern Matching for Switch Demonstration");
        System.out.println("--------------------------------------\n");
        
        // Create a list of different types of objects
        List<Object> objects = new ArrayList<>();
        objects.add("Hello World");
        objects.add(42);
        objects.add(3.14159);
        objects.add(List.of(1, 2, 3));
        objects.add(new StringBuilder("Dynamic Text"));
        objects.add(null);
        
        // Process each object using pattern matching
        System.out.println("Processing different types of objects:");
        System.out.println("-----------------------------------");
        
        for (Object obj : objects) {
            String result = analyzeObject(obj);
            System.out.println(result);
        }
        
        // Demonstrate more specific pattern matching
        System.out.println("\nDetailed number analysis:");
        System.out.println("----------------------");
        
        Object[] numbers = { 42, -17, 0, 3.14, 100L, 1000.0f };
        for (Object num : numbers) {
            String result = analyzeNumber(num);
            System.out.println(result);
        }
    }
    
    private static String analyzeObject(Object obj) {
        return switch (obj) {
            case null -> "Found null value";
            case String s -> String.format("String with length %d: '%s'", s.length(), s);
            case Integer i -> String.format("Integer value: %d (is %s)", 
                i, (i % 2 == 0 ? "even" : "odd"));
            case Double d -> String.format("Double value: %.2f", d);
            case List<?> list -> String.format("List with %d elements: %s", 
                list.size(), list);
            case StringBuilder sb -> String.format("StringBuilder content: '%s'", 
                sb.toString());
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }
    
    private static String analyzeNumber(Object obj) {
        return switch (obj) {
            case Integer i when i < 0 -> String.format("Negative integer: %d", i);
            case Integer i when i == 0 -> "Zero integer value";
            case Integer i -> String.format("Positive integer: %d", i);
            case Double d when d < 0 -> String.format("Negative double: %.2f", d);
            case Double d when d > 0 -> String.format("Positive double: %.2f", d);
            case Float f -> String.format("Float value: %.2f", f);
            case Long l -> String.format("Long value: %d", l);
            case Number n -> String.format("Other number type: %s", n);
            default -> "Not a number: " + obj.getClass().getSimpleName();
        };
    }
} 