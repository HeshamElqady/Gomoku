package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class SimpleGomokuLogic extends GameLogic{
    public SimpleGomokuLogic(Player player1, Player player2, int boardSize) {
        super(player1, player2, boardSize);
    }

    @Override
    public boolean checkWin(int row, int col, char symbol) {
            return countOccurrences(row, col, symbol, 1, 0) >= 5  // Vertical
                    || countOccurrences(row, col, symbol, 0, 1) >= 5   // Horizontal
                    || countOccurrences(row, col, symbol, 1, 1) >= 5 // Diagonal \
                    || countOccurrences(row, col, symbol, 1, -1) >= 5; // Diagonal /
    }

    @Override
    public boolean isForbiddenMove(Move move) {
        return false;
    }
}
