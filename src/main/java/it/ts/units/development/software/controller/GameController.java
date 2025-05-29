package it.ts.units.development.software.controller;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import it.ts.units.development.software.service.GameLogic;
import it.ts.units.development.software.service.RenjuVariantLogic;
import it.ts.units.development.software.service.SimpleGomokuLogic;
import it.ts.units.development.software.view.ConsoleView;

import java.util.Scanner;

public class GameController {
    private final Scanner scanner;
    private final ConsoleView view;
    private final GameLogic gameLogic;

    public GameController(Scanner scanner,ConsoleView view) {
        this.view = view;
        this.scanner = scanner;
        int boardSize = init();
        Player player1 = createPlayer('X');
        Player player2 = createPlayer('O');

        this.gameLogic = getGameLogic(boardSize, player1, player2);
    }

    private GameLogic getGameLogic(int boardSize, Player player1, Player player2) {
        while (true) {
            view.printGameChoice();
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("G")) {
                view.printGomokuWelcomeGame();
                return new SimpleGomokuLogic(player1, player2, boardSize);
            } else if (choice.equals("R")) {
                view.printRenjuGomokuWelcomeGame();
                String helpOrStart = scanner.nextLine().toUpperCase();
                if (helpOrStart.equals("H")) {
                    view.printHelpRenju();
                    return new RenjuVariantLogic(player1, player2, boardSize);
                } else if (helpOrStart.equals("S")) {
                    return new RenjuVariantLogic(player1, player2, boardSize);
                }
                else view.printInputError();
            } else {
                view.printInputError();
            }
        }
    }

    public Player createPlayer(char symbol) {
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