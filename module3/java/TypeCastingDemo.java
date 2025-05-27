public class TypeCastingDemo {
    public static void main(String[] args) {
        System.out.println("Type Casting Demonstration\n");
        
        // Double to Int casting (Explicit/Narrowing casting)
        double price = 99.99;
        int priceAsInt = (int) price;
        System.out.println("Original double value: " + price);
        System.out.println("After casting to int: " + priceAsInt);
        System.out.println("Notice: Decimal part is truncated\n");
        
        // Int to Double casting (Implicit/Widening casting)
        int number = 100;
        double numberAsDouble = number;  // No explicit cast needed
        System.out.println("Original int value: " + number);
        System.out.println("After converting to double: " + numberAsDouble);
        System.out.println("Notice: Conversion is exact\n");
        
        // More examples with calculations
        double pi = 3.14159;
        int roundedPi = (int) pi;
        System.out.println("Pi as double: " + pi);
        System.out.println("Pi cast to int: " + roundedPi);
        System.out.println("Notice: Decimal precision is lost\n");
        
        // Demonstrating casting with expressions
        int sum = 5;
        double divisor = 2.0;
        double result = sum / divisor;  // Results in double
        int truncatedResult = (int) result;  // Cast back to int
        
        System.out.println("Expression: " + sum + " / " + divisor);
        System.out.println("Result as double: " + result);
        System.out.println("Result cast to int: " + truncatedResult);
    }
} 