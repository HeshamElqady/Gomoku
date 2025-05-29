package it.ts.units.development.software.controller;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import it.ts.units.development.software.service.GameLogic;
import it.ts.units.development.software.view.ConsoleView;

import java.util.Scanner;

public class GameController {

    private final GameLogic gameLogic;
    private final ConsoleView view;
    private final Scanner scanner;

    public GameController() {
        this.view = new ConsoleView();
        this.scanner = new Scanner(System.in);
        int boardSize= init();
        Player player1 = createPlayer('X');
        Player player2 = createPlayer('O');

        this.gameLogic = new GameLogic(player1, player2, boardSize);
    }

    private Player createPlayer(char symbol) {
        view.printPlayerNamePrompt(symbol);
        String name = scanner.nextLine().trim();
        return new Player(name.isEmpty() ? "Player " + symbol : name, symbol);
    }

    public void startGame() {
        view.printWelcomeMessage();
        while (!gameLogic.isGameOver()) {
            playTurn();
        }
        view.displayBoard(gameLogic.getBoard());
        if (gameLogic.isDraw()) {
            view.printDraw();
        } else {
            view.printWinner(gameLogic.getCurrentPlayer());
        }
    }

    private void playTurn() {
        try {
            view.displayBoard(gameLogic.getBoard());
            Player currentPlayer = gameLogic.getCurrentPlayer();
            view.promptMove(currentPlayer);
            Move move = getPlayerMove();
            if (!gameLogic.makeMove(move)) {
                view.printOccupiedMove();
            }
        } catch (IndexOutOfBoundsException e) {
            view.printInvalidMove();
        }
    }

    private Move getPlayerMove() {
        while (true) {
            try {
                view.printMoveLogic();
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                return new Move(row, col);
            } catch (Exception e) {
                scanner.nextLine(); // Clear invalid input
                view.printInputError();
            }
        }
    }
    private int init() {
        while (true) {
            view.printWelcomeMessage();
            int boardSize = Integer.parseInt(scanner.nextLine());
            if (boardSize== 15 || boardSize==19) {
                return boardSize;
            }
            else view.printInputError();
        }
    }
}
