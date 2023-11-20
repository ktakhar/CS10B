class King extends Piece {
    public King() {
        super();
    }

    @Override
    public boolean moveToThisLocation(int row, int col) {
        if (!positionOnBoard(row, col)) {
            return false;
        }

        int currentRow = getRow();
        int currentCol = getCol();

        int rowDiff = Math.abs(row - currentRow);
        int colDiff = Math.abs(col - currentCol);

        return rowDiff <= 1 && colDiff <= 1; // King can move one square in any direction
    }
}