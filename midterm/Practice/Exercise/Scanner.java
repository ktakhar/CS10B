import java.util.Scanner;

class Scanner {
    public static void main (String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("How old are you? ");
        int age = keyboard.nextInt();

        System.out.println("Next year you'll be " + (age + 1) + "!");
    }
}