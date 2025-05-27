public class DataTypeDemo {
    public static void main(String[] args) {
        // Integer type
        int age = 25;
        System.out.println("Integer (int) example - Age: " + age);
        
        // Float type (note the 'f' suffix)
        float temperature = 98.6f;
        System.out.println("Float example - Temperature: " + temperature);
        
        // Double type (more precise decimal)
        double pi = 3.14159265359;
        System.out.println("Double example - Pi: " + pi);
        
        // Character type (single quotes for char)
        char grade = 'A';
        System.out.println("Character (char) example - Grade: " + grade);
        
        // Boolean type
        boolean isJavaFun = true;
        System.out.println("Boolean example - Is Java Fun?: " + isJavaFun);
        
        // Byte type (small integer from -128 to 127)
        byte smallNumber = 127;
        System.out.println("Byte example - Small Number: " + smallNumber);
        
        // Short type (larger than byte but smaller than int)
        short mediumNumber = 32000;
        System.out.println("Short example - Medium Number: " + mediumNumber);
        
        // Long type (note the 'L' suffix)
        long bigNumber = 9223372036854775807L;
        System.out.println("Long example - Big Number: " + bigNumber);
        
        // Demonstrating type sizes
        System.out.println("\nSize of each primitive type in bytes:");
        System.out.println("byte: " + Byte.SIZE/8);
        System.out.println("short: " + Short.SIZE/8);
        System.out.println("int: " + Integer.SIZE/8);
        System.out.println("long: " + Long.SIZE/8);
        System.out.println("float: " + Float.SIZE/8);
        System.out.println("double: " + Double.SIZE/8);
        System.out.println("char: " + Character.SIZE/8);
    }
} 