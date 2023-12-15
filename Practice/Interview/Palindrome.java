import java.util.Scanner;

class Palindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter #: ");

        try {
        int number = in.nextInt();

        boolean result = isPalindrome(number);

        if (result == true) {
            System.out.println(number + " is a palindrome");
        } else {
            System.out.println(number + " is not a palindrome");
        }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
        in.close();
        }
    }
    public static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return originalNumber == reversedNumber;
    }
}