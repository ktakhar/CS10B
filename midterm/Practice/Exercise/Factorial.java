class Factorial {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int input = s.nextInt();
        int result = facotorial(input);

        // int n = 5;
        // int result = factorial(n);

        System.out.println("Factorial of " + input + " is " + result);
    }

    public static int factorial(int n) {
        if (n == 0) { //base case
        return 1;
        } else {
            return n * factorial(n - 1);  // Recursive case
        }
    }
}