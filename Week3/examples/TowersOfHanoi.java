import java.util.Scanner;

public class TowersOfHanoi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of disks: ");
        int numDisks = scanner.nextInt();
        scanner.close();

        int moves = solveHanoi(numDisks, 'A', 'C', 'B');
        System.out.println("Number of moves: " + moves);
    }

    public static int solveHanoi(int n, char source, char target, char auxiliary) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + target);
            return 1; // Return 1 for the single move
        }

        int moves = 0;

        // Move n-1 disks from source to auxiliary peg using target peg as auxiliary
        moves += solveHanoi(n - 1, source, auxiliary, target);

        // Move the nth disk from source to target peg
        System.out.println("Move disk " + n + " from " + source + " to " + target);
        moves++;

        // Move n-1 disks from auxiliary peg to target peg using source peg as auxiliary
        moves += solveHanoi(n - 1, auxiliary, target, source);

        return moves;
    }
}
