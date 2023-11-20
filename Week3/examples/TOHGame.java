import java.util.Scanner;
import java.util.Stack;

public class TOHGame {

    private static final int NUM_DISKS = 3; // Change this to the desired number of disks
    private static Stack<Integer>[] towers = new Stack[3];
    private static int moves = 0;

    public static void main(String[] args) {
        initializeGame();
        displayGame();

        while (!isGameOver()) {
            playTurn();
        }

        System.out.println("Congratulations! You've solved the puzzle in " + moves + " moves.");
    }

    private static void initializeGame() {
        for (int i = 0; i < 3; i++) {
            towers[i] = new Stack<>();
        }
        for (int i = NUM_DISKS; i > 0; i--) {
            towers[0].push(i);
        }
    }

    private static void displayGame() {
        System.out.println("Current Towers of Hanoi:");
        for (int i = NUM_DISKS - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (!towers[j].isEmpty() && towers[j].size() > i) {
                    System.out.print("  " + towers[j].get(i) + "  ");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
        System.out.println("  A     B     C  ");
        System.out.println();
    }

    private static void playTurn() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter move (e.g., 'AB' to move from tower A to tower B): ");
        String move = scanner.next();

        if (move.length() == 2) {
            char fromTower = move.charAt(0);
            char toTower = move.charAt(1);

            int fromIndex = fromTower - 'A';
            int toIndex = toTower - 'A';

            if (isValidMove(fromIndex, toIndex)) {
                int disk = towers[fromIndex].pop();
                towers[toIndex].push(disk);
                moves++;
                displayGame();
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a move like 'AB'.");
        }
    }

    private static boolean isValidMove(int from, int to) {
        if (towers[from].isEmpty()) {
            return false;
        } else if (towers[to].isEmpty() || towers[from].peek() < towers[to].peek()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isGameOver() {
        return towers[2].size() == NUM_DISKS;
    }
}
