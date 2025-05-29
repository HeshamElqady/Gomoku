package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;

public class SimpleGomokuLogic extends GameLogic{
    public SimpleGomokuLogic(Player player1, Player player2, int boardSize) {
        super(player1, player2, boardSize);
    }

    @Override
    public boolean isForbiddenMove(Move move) {
        return false;
    }
}
