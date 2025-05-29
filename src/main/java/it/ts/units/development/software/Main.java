package it.ts.units.development.software;

import it.ts.units.development.software.controller.GameController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameController controller = new GameController();
        controller.startGame();
    }
}