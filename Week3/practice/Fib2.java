public class Fib2 {
    public static void main(String[] args) {
        int n = 10; // Number of Fibonacci numbers to generate

        // Create an integer array to store the Fibonacci numbers
        int[] fibonacci = new int[n];

        // Initialize the first two Fibonacci numbers
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        // Print the initial values of the Fibonacci sequence
        System.out.print(fibonacci[0] + " " + fibonacci[1] + " ");

        // Generate and print the remaining Fibonacci numbers
        for (int i = 2; i < n; i++) {
            // Calculate the next Fibonacci number by summing the two previous numbers
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            
            // Print the current Fibonacci number
            System.out.print(fibonacci[i] + " ");
        }
    }
}

