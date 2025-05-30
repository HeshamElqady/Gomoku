package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Board;
import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public abstract class GameLogic {
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

        try {
            if (isForbiddenMove(move)) {
                gameOver = true;
            }
        } catch (Exception e) {
            gameOver = true;
            switchTurn();
            throw e;
        }

        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public abstract boolean checkWin(int row, int col, char symbol);

    protected int countOccurrences(int row, int col, char symbol, int dRow, int dCol) { //  // returns how many times a piece with the same symbol occurs in a direction in both verses
        int count = 1; // start from 1 for the initial piece
        int size = board.getSize();

        // Check both directions
        for (int dir = -1; dir <= 1; dir += 2) { // -1 and +1
            int r = row + dir * dRow;
            int c = col + dir * dCol;

            while (r >= 0 && r < size && c >= 0 && c < size && board.getCell(r, c) == symbol) {
                count++;
                r += dir * dRow;
                c += dir * dCol;
            }
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

    public abstract boolean isForbiddenMove(Move move);
}
