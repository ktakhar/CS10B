import java.util.Scanner;

// Abstract class representing a chess piece
abstract class Piece {
    protected int pieceRow; // Current row of the piece on the chessboard
    protected int pieceCol; // Current column of the piece on the chessboard

    // Constructor
    public Piece() {
        pieceRow = 0;
        pieceCol = 0;
    }

    // Abstract method to check if the piece can move to a specific location
    abstract boolean moveToThisLocation(int row, int col);

    // Method to check if a position is within the 8x8 chessboard
    public boolean positionOnBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    // Getter for the current row of the piece
    public int getRow() {
        return pieceRow;
    }

    // Getter for the current column of the piece
    public int getCol() {
        return pieceCol;
    }

    // Method to place the piece on the chessboard by getting user input
    public void placeOnChessBoard() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Type ROW where piece is located: ");
        pieceRow = keyboard.nextInt();
        System.out.print("Type COLUMN where piece is located: ");
        pieceCol = keyboard.nextInt();
        if (pieceRow < 1 || pieceRow > 8 || pieceCol < 1 || pieceCol > 8) {
            System.out.print("Invalid input");
        }
        keyboard.nextLine();
    }
}

// Subclass for the Pawn piece
class Pawn extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = indexRow - pieceRow; // Calculate row difference

        // A pawn can move one square forward (up) on the same column
        return colDiff == 0 && rowDiff == 1;
    }
}

// Subclass for the Knight piece
class Knight extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = Math.abs(pieceRow - indexRow); // Calculate row difference

        // A knight can move in an L-shape: 2 squares in one direction and 1 square in the perpendicular direction
        return (colDiff == 2 && rowDiff == 1) || (colDiff == 1 && rowDiff == 2);
    }
}

// Subclass for the Bishop piece
class Bishop extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = Math.abs(pieceRow - indexRow); // Calculate row difference

        // A bishop can move diagonally (equal column and row differences)
        return colDiff == rowDiff;
    }
}

// Subclass for the Rook piece
class Rook extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = Math.abs(pieceRow - indexRow); // Calculate row difference

        // A rook can move horizontally (equal row difference) or vertically (equal column difference)
        return colDiff == 0 || rowDiff == 0;
    }
}

// Subclass for the Queen piece
class Queen extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = Math.abs(pieceRow - indexRow); // Calculate row difference

        // A queen can move horizontally, vertically, or diagonally (a combination of the previous conditions)
        return colDiff == 0 || rowDiff == 0 || colDiff == rowDiff;
    }
}

// Subclass for the King piece
class King extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = Math.abs(pieceCol - indexCol); // Calculate column difference
        int rowDiff = Math.abs(pieceRow - indexRow); // Calculate row difference

        // A king can move one square in any direction (horizontally, vertically, or diagonally)
        return colDiff <= 1 && rowDiff <= 1;
    }
}
