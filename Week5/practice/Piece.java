import java.util.Scanner;

abstract class Piece {
    protected int pieceRow;
    protected int pieceCol;

    public Piece() {

    }

    abstract boolean moveToThisLocation(int row, int col);
    public boolean positionOnBoard(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    public int getRow() {
        return pieceRow;
    }

    public int getCol() {
        return pieceCol;
    }

    public void placeOnChessBoard() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Type ROW where piece is located: ");
        pieceRow = keyboard.nextInt();
        System.out.print("Type COLUMN where piece is located: ");
        pieceCol = keyboard.nextInt();
        if (pieceRow > 1 || pieceRow > 8 || pieceCol < 1 || pieceCol > 8) {
            System.out.print("Invalid input");
        }
        keyboard.nextLine();
    }
}

class Bishop extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = pieceCol - indexCol;
        int rowDiff = pieceRow - indexRow;

        return (colDiff + rowDiff == 0) || (colDiff == rowDiff);
    }
}

class Knight extends Piece {
    boolean moveToThisLocation(int indexRow, int indexCol) {
        int colDiff = pieceCol - indexCol;
        int rowDiff = pieceRow - indexRow;

        return (colDiff * colDiff + rowDiff * rowDiff == 5) || (colDiff == 0 & rowDiff ==0);
    }
}