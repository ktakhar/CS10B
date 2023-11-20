import java.util.Scanner;

public class ReverseScanner {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        reverseString(keyboard);
        keyboard.close();
    }

    public static void reverseString(Scanner input) {
        if (input.hasNextLine()) {
            String line = input.nextLine();
            reverseString(input);
            System.out.println(line);
        }
    }
}
