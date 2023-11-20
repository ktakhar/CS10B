// Chess.java

/**
 * PSET #6
 * This program simulates a chessboard with different pieces (Bishop, Knight, and King).
 * It allows the user to select a piece type and displays the chessboard with valid moves for that piece.
 * It is an extension for the Chessboard program from Unit 5.
 * 
 * For the purpose of testing King.java
 * 
 * @author Kulijt Takhar
 * @version October 8, 2023
 */

import java.util.Scanner;

// Abstract class representing a chess piece

abstract class Piece {
    // Instance variables presenting the piece's row and column
    protected int pieceRow;
    protected int pieceColumn;

    // Constructor
    public Piece() {
        // Initialize the piece's row and column
    }

    // Abstract method to check if the piece can attack a location
    abstract boolean attackingThisLocation(int row, int col);

    // Method to check if a position is on the chessboard
    public boolean positionOnBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    // Getter method for row
    public int getRow() {
        return pieceRow;
    }

    // Getter method for column
    public int getCol() {
        return pieceColumn;
    }

    // Method to determine position on the chessboard
    public void placeOnChessBoard() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Type the ROW where your chess piece is located: ");
        pieceRow = keyboard.nextInt();
        System.out.print("Type the COLUMN where your chess piece is located: ");
        pieceColumn = keyboard.nextInt();
        if (pieceRow < 1 || pieceRow > 8 || pieceColumn < 1 || pieceColumn > 8) {
            System.out.print("Invalid input, but I'll try anyway");
        }
        keyboard.nextLine();
    }
}

// Bishop class extends Piece
class Bishop extends Piece {
    boolean attackingThisLocation(int indexRow, int indexColumn) {
        int columnDiff = pieceColumn - indexColumn;
        int rowDiff = pieceRow - indexRow;

        return (columnDiff + rowDiff == 0) || (columnDiff == rowDiff);
    }
}

// Knight class extends Piece
class Knight extends Piece {
    boolean attackingThisLocation(int indexRow, int indexColumn) {
        int columnDiff = pieceColumn - indexColumn;
        int rowDiff = pieceRow - indexRow;

        return (columnDiff * columnDiff + rowDiff * rowDiff == 5) || (columnDiff == 0 && rowDiff == 0);
    }
}

// Chess class to test the Piece class and its subclasses, specifically the King class.
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

        p.placeOnChessBoard(); // Place the selected piece on the chessboard

        System.out.println("\n  1 2 3 4 5 6 7 8"); // Number the columns

        for (int indexRow = 1; indexRow <= 8; indexRow++) {
            System.out.print(indexRow); // Number the rows
            for (int indexColumn = 1; indexColumn <= 8; indexColumn++) {
                if (p.attackingThisLocation(indexRow, indexColumn)) {
                    System.out.print(" *"); // Mark valid moves with an asterisk
                } else if ((indexColumn + indexRow) % 2 == 0) {
                    System.out.print(" b"); // Display black squares
                } else {
                    System.out.print(" w"); // Display white squares
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
