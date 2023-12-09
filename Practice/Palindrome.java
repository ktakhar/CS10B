import java.util.Scanner;

public class Palindrome {
    private int x;

    public Palindrome(int x) {
       this.x = x;
    }
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
    
        int original = x;
        int reversed = 0;

        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return original == reversed;
}


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number: ");
        int x = in.nextInt();
        

        if (isPalindrome(x)) {
            System.out.println(x + " is a palindrome.");
        } else {
            System.out.println(x + " is NOT a palindrome.");
        }
        in.close();
    }
    
}