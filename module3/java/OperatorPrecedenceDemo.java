public class OperatorPrecedenceDemo {
    public static void main(String[] args) {
        System.out.println("Operator Precedence Demonstration\n");
        
        // Example 1: Arithmetic operators
        int result1 = 10 + 5 * 2;
        System.out.println("Expression 1: 10 + 5 * 2");
        System.out.println("Result: " + result1);
        System.out.println("Explanation: Multiplication (*) has higher precedence than addition (+)");
        System.out.println("            So, 5 * 2 = 10 is evaluated first, then 10 + 10 = 20\n");
        
        // Example 2: Parentheses changing precedence
        int result2 = (10 + 5) * 2;
        System.out.println("Expression 2: (10 + 5) * 2");
        System.out.println("Result: " + result2);
        System.out.println("Explanation: Parentheses have highest precedence");
        System.out.println("            So, (10 + 5) = 15 is evaluated first, then 15 * 2 = 30\n");
        
        // Example 3: Multiple operators
        int result3 = 20 - 4 * 2 + 8 / 2;
        System.out.println("Expression 3: 20 - 4 * 2 + 8 / 2");
        System.out.println("Result: " + result3);
        System.out.println("Explanation: Multiplication and division have equal precedence (evaluated left to right)");
        System.out.println("            1. 4 * 2 = 8");
        System.out.println("            2. 8 / 2 = 4");
        System.out.println("            3. 20 - 8 + 4 = 16\n");
        
        // Example 4: Assignment and arithmetic
        int x = 5;
        int y = 3;
        int result4 = x += y * 2;
        System.out.println("Expression 4: x += y * 2 (where x = 5, y = 3)");
        System.out.println("Result: " + result4);
        System.out.println("Explanation: Multiplication happens before addition assignment");
        System.out.println("            1. y * 2 = 6");
        System.out.println("            2. x += 6 (equivalent to x = x + 6)\n");
        
        // Example 5: Logical operators
        boolean result5 = true && false || true;
        System.out.println("Expression 5: true && false || true");
        System.out.println("Result: " + result5);
        System.out.println("Explanation: && (AND) has higher precedence than || (OR)");
        System.out.println("            1. true && false = false");
        System.out.println("            2. false || true = true\n");
        
        // Example 6: Mixed operators
        int result6 = 10 * 2 + 3 * (2 + 1) - 4 / 2;
        System.out.println("Expression 6: 10 * 2 + 3 * (2 + 1) - 4 / 2");
        System.out.println("Result: " + result6);
        System.out.println("Explanation: Evaluation order:");
        System.out.println("            1. (2 + 1) = 3         [parentheses first]");
        System.out.println("            2. 10 * 2 = 20         [multiplication]");
        System.out.println("            3. 3 * 3 = 9           [multiplication]");
        System.out.println("            4. 4 / 2 = 2           [division]");
        System.out.println("            5. 20 + 9 - 2 = 27     [addition and subtraction left to right]");
    }
} 