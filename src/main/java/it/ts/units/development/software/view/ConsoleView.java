package it.ts.units.development.software.view;

import it.ts.units.development.software.model.Board;
import it.ts.units.development.software.model.Player;
import it.ts.units.development.software.util.Constants;

public class ConsoleView {
    public void printWelcomeMessage() {
        System.out.println(Constants.INITIALIZE_GAME);
    }

    public void displayBoard(Board board) {
        board.printBoard();
    }

    public void promptMove(Player player) {
        System.out.printf(Constants.PLAYER_TO_MOVE, player.getName(), player.getSymbol());

    }

    public void printInvalidMove() {
        System.out.println(Constants.ERROR_INVALID_MOVE);
    }

    public void printOccupiedMove() {
        System.out.println(Constants.ERROR_CELL_OCCUPIED);
    }

    public void printWinner(Player winner) {
        System.out.printf(Constants.GAME_ENDED_PLAYER_WINS, winner.getName());
    }

    public void printDraw() {
        System.out.printf(Constants.GAME_ENDED_BY_DRAW);
    }

    public void printInputError() {
        System.out.println(Constants.ERROR_INVALID_INPUT);
    }

    public void printMoveLogic() {
        System.out.println(Constants.PLACE_PIECE);
    }

    public void pintBlackPlayerInsertion() {
        System.out.println(Constants.PLAYER_X);
    }

    public void pintWhitePlayerInsertion() {
        System.out.println(Constants.PLAYER_O);
    }

    public void printPlayerNamePrompt(char symbol) {
        if (symbol == 'X') {
            System.out.println(Constants.PLAYER_X);
        } else {
            System.out.println(Constants.PLAYER_O);
        }
    }
}
