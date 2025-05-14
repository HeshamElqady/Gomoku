package it.ts.units.development.software;

import java.util.Scanner;

public class Gomoku {
    private static final int SIZE = 15; // Size of the Gomoku board
    private static final char EMPTY = '.'; // Empty cell symbol
    private static final char PLAYER_ONE = 'X'; // Player 1 symbol
    private static final char PLAYER_TWO = 'O'; // Player 2 symbol
    private char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        Gomoku game = new Gomoku();
        game.play();
    }

    public Gomoku() {
        initializeBoard();
    }

    // Initialize the game board
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Main game loop
    public void play() {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = PLAYER_ONE;
        boolean gameWon = false;

        System.out.println("Welcome to Gomoku! (5 in a row to win)");
        printBoard();

        while (!gameWon) {
            System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "1 (X)" : "2 (O)") + "'s turn.");
            int row, col;
            while (true) {
                System.out.print("Enter row and column (0-" + (SIZE - 1) + "): ");
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (isValidMove(row, col)) {
                    break;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }

            makeMove(row, col, currentPlayer);
            printBoard();

            if (checkWin(row, col, currentPlayer)) {
                System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "1 (X)" : "2 (O)") + " wins!");
                gameWon = true;
            } else {
                currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE; // Switch players
            }
        }
        scanner.close();
    }

    // Check if the move is valid
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    // Make a move on the board
    private void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    // Print the game board
    private void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if a player has won
    private boolean checkWin(int row, int col, char player) {
        return checkDirection(row, col, player, 1, 0) || // Horizontal
               checkDirection(row, col, player, 0, 1) || // Vertical
               checkDirection(row, col, player, 1, 1) || // Diagonal (/)
               checkDirection(row, col, player, 1, -1);  // Diagonal (\)
    }

    // Check a direction for 5 in a row
    private boolean checkDirection(int row, int col, char player, int deltaRow, int deltaCol) {
        int count = 1;
        count += countInDirection(row, col, player, deltaRow, deltaCol);
        count += countInDirection(row, col, player, -deltaRow, -deltaCol);
        return count >= 5;
    }

    // Count consecutive pieces in one direction
    private int countInDirection(int row, int col, char player, int deltaRow, int deltaCol) {
        int count = 0;
        int r = row + deltaRow;
        int c = col + deltaCol;
        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == player) {
            count++;
            r += deltaRow;
            c += deltaCol;
        }
        return count;
    }
}

