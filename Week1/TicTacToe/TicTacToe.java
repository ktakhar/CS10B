import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        boolean gameOver = false;
        char currentPlayer = 'X';

        while (!gameOver) {
            displayBoard(board);
            int[] move = getPlayerMove(board, currentPlayer);
            int row = move[0];
            int col = move[1];

            if (isValidMove(board, row, col)) {
                board[row][col] = currentPlayer;
                if (isWin(board, currentPlayer)) {
                    displayBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isBoardFull(board)) {
                    displayBoard(board);
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    public static void displayBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static int[] getPlayerMove(char[][] board, char currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();
        return move;
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ');
    }

    public static boolean isWin(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check main diagonal
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Check anti-diagonal
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
