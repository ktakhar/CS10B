class Fib {
    public static void main(String[] args) {
        int n = 10; // number of Fibonacci numbers to generate
        int a = 0; // current Fibonacci number
        int b = 1; // next Fibonacci number

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
    }
}