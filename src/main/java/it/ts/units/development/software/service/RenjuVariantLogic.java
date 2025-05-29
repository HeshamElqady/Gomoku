package it.ts.units.development.software.service;

import it.ts.units.development.software.exception.IllegalDoubleFourException;
import it.ts.units.development.software.exception.IllegalDoubleThreeException;
import it.ts.units.development.software.exception.IllegalOverlineException;
import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class RenjuVariantLogic extends GameLogic {

    private int threes;
    private int fours;
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
        threes = 0;
        fours = 0;

        if (getCurrentPlayer().getSymbol() != 'X') {
            return false;
        }

        if (verticalOcc == 3) {
            threes++;
        } else if (verticalOcc == 4) {
            fours++;
        } else if (verticalOcc > 5) {
            throw new IllegalOverlineException();
        }


        if (horizontalOcc == 3) {
            threes++;
        } else if (horizontalOcc == 4) {
            fours++;
        } else if (horizontalOcc > 5) {
            throw new IllegalOverlineException();
        }


        if (diagonalOcc == 3) {
            threes++;
        } else if (diagonalOcc == 4) {
            fours++;
        } else if (diagonalOcc > 5) {
            throw new IllegalOverlineException();
        }


        if (inverseDiagonalOcc == 3) {
            threes++;
        } else if (inverseDiagonalOcc == 4) {
            fours++;
        } else if (inverseDiagonalOcc > 5) {
            throw new IllegalOverlineException();
        }
        if (threes >= 2) {
            throw new IllegalDoubleThreeException();
        } else if (fours >= 2) {
            throw new IllegalDoubleFourException();
        }
        return false;
    }

}
