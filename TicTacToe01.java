/*
 * The following program will simulate a TicTakToe game between two players as X and O.
 * This TicTakToe game will follow conventional rules, 3x3 tile array, three Xs or Os equals a win. 
 * Will also ask if players would like to play again. 
 */

// Import list 
import java.util.Scanner;

public class TicTacToe01 {
    private static final int ROWS = 3; // CONSTANT ROWS
    private static final int COLS = 3; // CONSTANT COLS
    private static String[][] board = new String[ROWS][COLS]; // Creation of a 3x3 board
    private static String currentPlayer = "X"; // Default player: X

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do { // This clears the board for the next game and resets conditions.
            clearBoard();
            boolean gameWon = false;
            boolean gameTied = false;
            int moves = 0;

            while (!gameWon && !gameTied) {
                displayBoard();
                int row, col;
                do {
                    row = SafeInput.getRangedInt(scanner, "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(scanner, "Enter column (1-3)", 1, 3) - 1;
                } while (!isValidMove(row, col));

                board[row][col] = currentPlayer;
                moves++;

                if (moves >= 5) { // Minimum moves needed to win
                    gameWon = isWin(currentPlayer);
                }

                if (!gameWon && moves == 9) {
                    gameTied = isTie();
                }

                if (gameWon) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (gameTied) {
                    displayBoard();
                    System.out.println("It's a tie!");
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(scanner, "Do you want to play again?");
        } while (playAgain);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
        currentPlayer = "X";
    }

    private static void displayBoard() {
        System.out.println("\n  1 2 3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1)
                System.out.println("  -----");
        }
    }

    // Private methods

    // Checks for valid moves.
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
