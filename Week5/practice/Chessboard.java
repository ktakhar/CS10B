import java.util.Scanner;

public class Chessboard {
    public static void main(String[] args) {
        Piece p;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Would you like to play with a Bishop, Knight, or King? ");
        String answer = keyboard.nextLine();

        if (answer.equalsIgnoreCase("Bishop")) {
            p = new Bishop();
        } else if (answer.equalsIgnoreCase("Knight") || answer.equalsIgnoreCase("N")) {
            p = new Knight();
        } else if (answer.equalsIgnoreCase("King") || answer.equalsIgnoreCase("K")) {
            p = new King();
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        p.placeOnChessBoard();

        System.out.println("\n  1 2 3 4 5 6 7 8");

        for (int indexRow = 1; indexRow <= 8; indexRow++) {
            System.out.print(indexRow);
            for (int indexCol = 1; indexCol <= 8; indexCol++) {
                if (p.moveToThisLocation(indexRow, indexCol)) {
                    System.out.print(" *");
                } else if ((indexCol + indexRow) % 2 == 0) {
                    System.out.print(" b");
                } else {
                    System.out.print(" w");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
