import java.util.Scanner;

public class Chessboard {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        // Display the empty chessboard before entering the loop
        displayEmptyChessboard();
        
        // Start an infinite loop to repeatedly ask for the user's choice
        while (true) {
            // Prompt the user for their choice of chess piece or to exit
            System.out.print("Would you like to play with a Pawn, Bishop, Knight, Rook, Queen, or King? (Type 'exit' to quit) ");
            String answer = keyboard.nextLine();

            // Check if the user wants to exit the program
            if (answer.equalsIgnoreCase("exit")) {
                System.out.println("Exiting.");
                break; // Exit the loop
            }
            
            Piece piece; // Declare a reference to a Piece object

            // Check the user's choice and create an instance of the selected chess piece
            if (answer.equalsIgnoreCase("Pawn") || answer.equalsIgnoreCase("P")) {
                piece = new Pawn();
            } else if (answer.equalsIgnoreCase("Bishop") || answer.equalsIgnoreCase("B")) {
                piece = new Bishop();
            } else if (answer.equalsIgnoreCase("Knight") || answer.equalsIgnoreCase("N")) {
                piece = new Knight();
            } else if (answer.equalsIgnoreCase("Rook") || answer.equalsIgnoreCase("R")) {
                piece = new Rook();
            } else if (answer.equalsIgnoreCase("Queen") || answer.equalsIgnoreCase("Q")) {
                piece = new Queen();
            } else if (answer.equalsIgnoreCase("King") || answer.equalsIgnoreCase("K")) {
                piece = new King();
            } else {
                // Invalid choice, ask the user to try again and continue the loop
                System.out.println("Invalid choice. Try again.");
                continue; // Restart the loop
            }

            // Ask the user for the piece's position on the chessboard
            piece.placeOnChessBoard();

            // Display the chessboard with the selected piece's possible moves
            System.out.println("\n  1 2 3 4 5 6 7 8");

            for (int indexRow = 1; indexRow <= 8; indexRow++) {
                System.out.print(indexRow);
                for (int indexCol = 1; indexCol <= 8; indexCol++) {
                    if (piece.moveToThisLocation(indexRow, indexCol)) {
                        System.out.print(" *"); // Mark the valid move with an asterisk
                    } else if ((indexCol + indexRow) % 2 == 0) {
                        System.out.print(" □"); // Display black squares as 'b'
                    } else {
                        System.out.print(" □"); // Display white squares as 'w'
                    }
                }
                System.out.println();
            }
        }
    }

    // Helper method to display an empty chessboard
    private static void displayEmptyChessboard() {
        System.out.println("\n  1 2 3 4 5 6 7 8");

        for (int indexRow = 1; indexRow <= 8; indexRow++) {
            System.out.print(indexRow + " ");
            for (int indexCol = 1; indexCol <= 8; indexCol++) {
                if ((indexCol + indexRow) % 2 == 0) {
                    System.out.print("□ "); // Display black squares as '□'
                } else {
                    System.out.print("□ "); // Display white squares as '□'
                }
            }
            System.out.println();
        }
    }
}
