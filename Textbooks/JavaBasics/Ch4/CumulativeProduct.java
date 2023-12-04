import java.util.Scanner;

class CumulativeProduct {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many numbers? ");
        int count = in.nextInt();

        int product = 1;
        for (int i = 1; i <= count; i++) {
            System.out.print("Next number --> ");
            int number = in.nextInt();
            product *= number;
        }
        System.out.println("Product = " + product);
    }
}