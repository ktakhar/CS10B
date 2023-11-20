// King.java

/**
 * PSET2 #6
 * King class that extends Piece with method that checks if a King can attack a location
 * 
 * @author Kuljit Takhar
 * @version October 7, 2023
 */

class King extends Piece {
    public King() {
        // Call the constructor of the superclass (Piece)
        super(); 
    }

    // Override the attackingThisLocation method for the King piece
    @Override
    public boolean attackingThisLocation(int row, int col) {
        if (!positionOnBoard(row, col)) {
            return false;
        }

        // Get the current row and column of the piece
        int currentRow = getRow();
        int currentCol = getCol();

        // Calculate the difference between the current position and the position we are checking
        int rowDiff = Math.abs(row - currentRow);
        int colDiff = Math.abs(col - currentCol);

        // King can move one square in any direction
        return rowDiff <= 1 && colDiff <= 1;
    }
}