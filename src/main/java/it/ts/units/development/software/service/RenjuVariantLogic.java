package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class RenjuVariantLogic extends GameLogic {
    public RenjuVariantLogic(Player player1, Player player2, int boardSize) {
        super(player1, player2, boardSize);
    }

    @Override
    public boolean isForbiddenMove(Move move) {
        int threes = 0;
        int fours = 0;

        if (getCurrentPlayer().getSymbol() != 'X') {
            return false;
        }

        int verticalCount = countLeftRight(move.getRow(), move.getCol(), 1, 0);
        if (verticalCount == 3) {
            threes++;
        } else if (verticalCount == 4) {
            fours++;
        } else if (verticalCount > 5) {
            return true;
        }
        int horizontalCount = countLeftRight(move.getRow(), move.getCol(), 0, 1);
        if (horizontalCount == 3) {
            threes++;
        } else if (horizontalCount == 4) {
            fours++;
        } else if (horizontalCount > 5) {
            return true;
        }
        int diagonalCount = countLeftRight(move.getRow(), move.getCol(), 1, 1);
        if (diagonalCount == 3) {
            threes++;
        } else if (diagonalCount == 4) {
            fours++;
        } else if (diagonalCount > 5) {
            return true;
        }
        int inverseDiagonalCount = countLeftRight(move.getRow(), move.getCol(), 1, -1);
        if (inverseDiagonalCount == 3) {
            threes++;
        } else if (inverseDiagonalCount == 4) {
            fours++;
        } else if (inverseDiagonalCount > 5) {
            return true;
        }
        return threes >= 2 || fours >= 2;
    }

    private int countLeftRight(int row, int col, int dRow, int dCol) {
        int count = 1;

        count += countDirection(row, col, 'X', dRow, dCol);
        count += countDirection(row, col, 'X', -dRow, -dCol);

        return count;

    }
}
