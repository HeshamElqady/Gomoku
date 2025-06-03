package it.ts.units.development.software.controller;

import it.ts.units.development.software.exception.IllegalDoubleFourException;
import it.ts.units.development.software.exception.IllegalDoubleThreeException;
import it.ts.units.development.software.exception.IllegalOverlineException;
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
    private GameLogic gameLogic;

    public GameController(Scanner scanner, ConsoleView view) {
        this.view = view;
        this.scanner = scanner;
    }

    private void initializeGame() throws InterruptedException {
        int result;
        while (true) {
            view.printWelcomeMessage();
            Thread.sleep(1000);
            int boardSize1 = 0;
            try {
                boardSize1 = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                view.printInputError();
                continue;
            }
            if (boardSize1 == 15 || boardSize1 == 19) {
                result = boardSize1;
                break;
            } else view.printInputError();
        }
        int boardSize = result;
        Thread.sleep(1000);
        Player player1 = createPlayer('X');
        Thread.sleep(1000);
        Player player2 = createPlayer('O');

        this.gameLogic = getGameLogic(boardSize, player1, player2);
    }

    private GameLogic getGameLogic(int boardSize, Player player1, Player player2) throws InterruptedException {
        while (true) {
            view.printGameChoice();
            Thread.sleep(1000);
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
                } else view.printInputError();
            } else {
                view.printInputError();
            }
        }
    }

    private Player createPlayer(char symbol) {
        view.printPlayerNamePrompt(symbol);
        String name = scanner.nextLine().trim();
        return new Player(name.isEmpty() ? "Player " + symbol : name, symbol);
    }

    public void startGame() throws InterruptedException {
        initializeGame();
        Thread.sleep(3000);
        while (!gameLogic.isGameOver()) {
            playTurn();
        }
        Thread.sleep(3000);
        view.displayBoard(gameLogic.getBoard());
        Thread.sleep(1000);
        if (gameLogic.isDraw()) {
            view.printDraw();
        } else {
            view.printWinner(gameLogic.getCurrentPlayer());
        }
    }

    private void playTurn() {
        try {
            view.displayBoard(gameLogic.getBoard());
            Thread.sleep(2000);
            Player currentPlayer = gameLogic.getCurrentPlayer();
            view.promptMove(currentPlayer);
            Move move = getPlayerMove();
            Thread.sleep(1000);
            if (!gameLogic.makeMove(move)) {
                view.printOccupiedMove();
            }
        } catch (IndexOutOfBoundsException e) {
            view.printInvalidMove();
        } catch (IllegalDoubleThreeException e){
            view.printDoubleThree();
        }catch (IllegalDoubleFourException e){
            view.printDoubleFour();
        }catch (IllegalOverlineException e){
            view.printOverlineError();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

}