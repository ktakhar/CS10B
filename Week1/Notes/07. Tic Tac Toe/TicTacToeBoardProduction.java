// TicTacToeBoardProduction.java

/**
 * THIS IS THE FINISHED VERSION OF THE TicTacToeBoard template class.
 * TicTacToeBoard is a template class that models a Tic Tac Toe board.
 *
 * @author  David Habermehl
 * @version Last modified 23_Jan_2021
 **/
class TicTacToeBoardProduction {



    // Instance variables
    private int size, unplayedSquares;
    private char[][] ticTacToeBoard;



    /**
     * TicTacToeBoard constructors.
     *
     * @param   size      Number of rows and columns for the board.
     */
    public TicTacToeBoardProduction (int size) {
        this.size = size;
        this.unplayedSquares = size*size;

        // For simplicity, we'll ignore row 0 and column 0 and only deal with rows 1-to-size and columns 1-to-size
        this.ticTacToeBoard = new char[this.size+1][this.size+1];
     // for ( char[] row : ticTacToeBoard ) {
     //     for ( char column : row ) { column = 'K'; }
     // }
        for ( int row=1; row<=size; row++ ) {
            for ( int col=1; col<=size; col++ ) {
                this.ticTacToeBoard[row][col] = ' ';
            }
        }
    }
    public TicTacToeBoardProduction () { this( 0 ); }



    /**
     * The toString() method returns a string representation of the board.
     *
     * @return   string representation of the board.
     */
    public String toString () {
        String ticTacToeBoardString = "Game board:\n";
        for ( int row=1; row<=this.size; row++ ) {
            ticTacToeBoardString += "| ";
            for ( int col=1; col<=this.size; col++ ) {
                ticTacToeBoardString += String.format ( "%c ", this.ticTacToeBoard[row][col] );
            }
            ticTacToeBoardString += "|\n";
        }
        return ticTacToeBoardString;
    }



    /**
     * makeMove validates row and column. If they're valid, update board's state and return true.
     * Otherwise returns false.
     *
     * @param   row      row number.
     * @param   col      column number.
     * @param   c        'X' or 'O'.
     */
    public boolean makeMove  (int row, int col, char c ) {
        if ( row<1 || row>this.size || col<1 || col>this.size || this.ticTacToeBoard[row][col] != ' ' ) { return false; }
        else {
            this.unplayedSquares--;
            this.ticTacToeBoard[row][col] = c;
            return true;
        }
    }



    /**
     * The isTie() method returns a boolean indicating if the board state is a tie.
     *
     * @return   true if the board is a tie. false otherwise.
     */
    public boolean isTie () {
        return this.unplayedSquares == 0;
    }



    /**
     * The isWin() method returns a boolean indicating if the board state is a win.
     *
     * @return   true if the board is a win for either 'X' or 'O', false otherwise.
     */
    public boolean isWin () {
        // Check each row and column
        for ( int rowOrColumn=1; rowOrColumn<=this.size; rowOrColumn++ ) {
            if ( checkRowColumnOrDiagonal ( rowOrColumn, 0, 1, 1 ) ) { return true; } // check row rowOrColumn
            if ( checkRowColumnOrDiagonal ( 1, 1, rowOrColumn, 0 ) ) { return true; } // check column rowOrColumn
        }

        // Check \ diagonal starting at this.ticTacToeBoard[1][1] thru this.ticTacToeBoard[this.size][this.size]
        if ( checkRowColumnOrDiagonal ( 1, 1, 1, 1 ) ) { return true; }

        // Check / diagonal starting at this.ticTacToeBoard[this.size][1] thru this.ticTacToeBoard[1][this.size]
        if ( checkRowColumnOrDiagonal ( this.size, -1, 1, 1 ) ) { return true; }

        return false;
    }



    /**
     * checkRowColumnOrDiagonal returns true if specified row, column, or diagonal are the same non-blank char.
     *
     * @param   row           Starting row.
     * @param   rowIncrement  0 if we're checking a row because then row stays constant, else 1 or -1.
     * @param   col           Starting column.
     * @param   colIncrement  0 if we're checking a column because then col stays constant, else 1 or -1.
     * @return  true if specified row, column, or diagonal are the same non-blank char..
     */
    private boolean checkRowColumnOrDiagonal ( int row, int rowIncrement, int col, int colIncrement ) {
        char firstSquare = this.ticTacToeBoard[row][col];
        if ( firstSquare == ' ' ) { return false; }
        else {
            for ( int i=2; i<=this.size; i++ ) {
                row += rowIncrement;
                col += colIncrement;
                if ( firstSquare != this.ticTacToeBoard[row][col] ) { return false; }
            }
            return true;
        }
    }
}
