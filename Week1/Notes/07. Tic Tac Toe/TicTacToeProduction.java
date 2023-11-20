// TicTacToeProduction.java

/**
 * THIS IS THE FINISHED VERSION OF THE TicTacToe program class.
 * TicTacToe is a program class that plays a game of Tic Tac Toe.
 * It leverages an instance of the TicTacToeBoard template class.
 * We'll work on this class before we work on the template class because
 * this class defines what capabilities the template class needs to expose.
 *
 * @author  David Habermehl
 * @version Last modified 23_Jan_2021
 **/
import java.util.Scanner;
class TicTacToeProduction {
    private static final int SIZE = 3;

    public static void main(String [] args) {
        playGame();
    }



    /**
     * main() outsources the "heavy lifting" to the playGame  method
     */
    private static void playGame() {
        Scanner keyboard = new Scanner( System.in );
        String winner;
        TicTacToeBoardProduction ticTacToeBoard = new TicTacToeBoardProduction ( SIZE );
        System.out.printf( "New constructed bboard:\n%s\n\n", ticTacToeBoard );

        System.out.println ( "Welcome! Tic-Tac-Toe is a two player game." );
        String player1 = getInput( keyboard, "Enter player one's name: " );
        String player2 = getInput( keyboard, "Enter player two's name: " );
        System.out.println ( "\nPlayers take turns marking a square. Only squares\n" +
                             "not already marked can be picked. Once a player has\n" +
                             "marked three squares in a row, he or she wins! If all squares\n" +
                             "are marked and no three squares are the same, a tied game is declared.\n" +
                             "Have Fun!"
                           );

        // Display the initial, empty board via the template class's toString() method.
        System.out.printf ( "\n\n%s", ticTacToeBoard );

        // Terminology: "game is resolved" means we have a winner or a tie.
        // The loop executes player1's move. If the game isn't resolved, it executes player2's move.
        // It repeats until one of the player's moves resolves the game.
        do {
            winner = play( keyboard, player1, 'X', ticTacToeBoard );
            if ( winner == null ) { winner = play( keyboard, player2, 'O', ticTacToeBoard ); }
        } while ( winner == null );

        // The loop terminates when the game is resolved.
        System.out.print( "\n\nGame Over - " );
        if ( winner.isEmpty() ) { System.out.println( "It's A Tie!\n"); }
        else                    { System.out.printf( "%s WINS\n\n", winner ); }
    }



    /**
     * The play() method executes a player's move.
     *
     * @param   keyboard        Scanner object through which we'll communicate with the user.
     * @param   player          String naming the player.
     * @param   marker          'X' or 'O' depending on which player it is.
     * @param   ticTacToeBoard  Template class instance representing the board.
     * @return  null for no winner no tie, "" for tie, winning player's name for a win.
     */
    private static String play( Scanner keyboard, String player, char marker, TicTacToeBoardProduction ticTacToeBoard ) {
        int row, col;
        boolean illegalMove;

        // player takes their turn
        System.out.printf( "\nIt is %s's turn\n", player );

        // Pass user input for row and column to template class.
        // If template class complains, repeat.
        // If template class doesn't complain, it updated the board's state accordingly
        do {
            // Assume input is valid number. Template class will validate the number's magnitude.
            row = Integer.parseInt( getInput ( keyboard, String.format ( "Pick a row between 1 and %d: ", SIZE ) ) );
            col = Integer.parseInt( getInput ( keyboard, String.format ( "Pick a column between 1 and %d: ", SIZE ) ) );
            illegalMove = !ticTacToeBoard.makeMove( row, col, marker );
            if ( illegalMove ) { System.out.println( "ILLEGAL CHOICE! TRY AGAIN..." ); }
        } while ( illegalMove );

        // Display the updated board via the template class's toString() method.
        System.out.printf ( "\n\n%s", ticTacToeBoard );

        // Done making the player's move. Return appropriate status.
        if      ( ticTacToeBoard.isWin() ) { return player; }
        else if ( ticTacToeBoard.isTie() ) { return ""; }
        else                               { return null; }
    }



    /**
     * The getInput() method prompts the user and returns their response.
     *
     * @param   keyboard    Scanner object through which we'll communicate with the user.
     * @param   prompt      The String with which we'll prompt the user.
     * @return              The user's response.
     */
    private static String getInput( Scanner keyboard, String prompt ) {
        System.out.print( prompt );
        return keyboard.nextLine().trim();
    }
}
