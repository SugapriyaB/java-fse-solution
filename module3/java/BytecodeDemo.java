public class BytecodeDemo {
    public int calculateFactorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * calculateFactorial(n - 1);
    }

    public static void main(String[] args) {
        BytecodeDemo demo = new BytecodeDemo();
        int result = demo.calculateFactorial(5);
        System.out.println("Factorial of 5 is: " + result);
    }
} 