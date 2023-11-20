// Iterative Solution (non - cs10b style)

public class Fib {

    public static void main(String[] args) {
        int n = 10; // Change this value to calculate Fibonacci for a different number
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        
        int fib1 = 0;
        int fib2 = 1;
        int result = 0;
        
        for (int i = 2; i <= n; i++) {
            result = fib1 + fib2;
            fib1 = fib2;
            fib2 = result;
        }
        
        return result;
    }
}
