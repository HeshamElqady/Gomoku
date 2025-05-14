package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Board;
import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class GameLogic {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private boolean gameOver = false;

    private boolean draw = false;

    public GameLogic(Player player1, Player player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(boardSize);
        this.currentPlayer = player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean makeMove(Move move) {
        if (gameOver) return false;

        int row = move.getRow();
        int col = move.getCol();
        char symbol = currentPlayer.getSymbol();

        if (!board.isCellEmpty(row, col)) {
            return false;
        }

        board.placeMove(row, col, symbol);

        if (checkWin(row, col, symbol)) {
            gameOver = true;
            return true;
        }

        if (isBoardFull()) {
            gameOver = true;
            draw = true;
            return true;
        }

        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean checkWin(int row, int col, char symbol) {
        return checkDirection(row, col, symbol, 1, 0)   // Vertical
               || checkDirection(row, col, symbol, 0, 1)   // Horizontal
               || checkDirection(row, col, symbol, 1, 1)   // Diagonal \
               || checkDirection(row, col, symbol, 1, -1); // Diagonal /
    }

    private boolean checkDirection(int row, int col, char symbol, int dRow, int dCol) {
        int count = 1;

        count += countDirection(row, col, symbol, dRow, dCol);
        count += countDirection(row, col, symbol, -dRow, -dCol);

        return count >= 5;
    }

    private int countDirection(int row, int col, char symbol, int dRow, int dCol) {
        int count = 0;
        int size = board.getSize();

        int r = row + dRow;
        int c = col + dCol;

        while (r >= 0 && r < size && c >= 0 && c < size && board.getCell(r, c) == symbol) {
            count++;
            r += dRow;
            c += dCol;
        }

        return count;
    }

    private boolean isBoardFull() {
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isCellEmpty(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }
}
