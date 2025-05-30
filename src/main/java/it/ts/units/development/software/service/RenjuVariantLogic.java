package it.ts.units.development.software.service;

import it.ts.units.development.software.exception.IllegalDoubleFourException;
import it.ts.units.development.software.exception.IllegalDoubleThreeException;
import it.ts.units.development.software.exception.IllegalOverlineException;
import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class RenjuVariantLogic extends GameLogic {

    private int verticalOcc;
    private int horizontalOcc;
    private int diagonalOcc;
    private int inverseDiagonalOcc;

    public RenjuVariantLogic(Player player1, Player player2, int boardSize) {
        super(player1, player2, boardSize);
    }

    @Override
    public boolean checkWin(int row, int col, char symbol) {
        verticalOcc = countOccurrences(row, col, symbol, 1, 0);
        horizontalOcc = countOccurrences(row, col, symbol, 0, 1);
        diagonalOcc = countOccurrences(row, col, symbol, 1, 1);
        inverseDiagonalOcc = countOccurrences(row, col, symbol, 1, -1);

        if (getCurrentPlayer().getSymbol() != 'X') {
            return verticalOcc >= 5
                    || horizontalOcc >= 5
                    || diagonalOcc >= 5
                    || inverseDiagonalOcc >= 5;

        } else return verticalOcc == 5
                || horizontalOcc == 5
                || diagonalOcc == 5
                || inverseDiagonalOcc == 5;
    }

    @Override
    public boolean isForbiddenMove(Move move) {
        int threes = 0;
        int fours = 0;

        if (getCurrentPlayer().getSymbol() != 'X') {
            return false;
        }

        int[] occurrences = { verticalOcc, horizontalOcc, diagonalOcc, inverseDiagonalOcc };

        for (int occ : occurrences) {
            switch (occ) {
                case 3:
                    threes++;
                    break;
                case 4:
                    fours++;
                    break;
                default:
                    if (occ > 5) { throw new IllegalOverlineException(); }
                    break;
            }
        }
        if (threes >= 2) {
            throw new IllegalDoubleThreeException();
        } else if (fours >= 2) {
            throw new IllegalDoubleFourException();
        }
        return false;
    }

}
