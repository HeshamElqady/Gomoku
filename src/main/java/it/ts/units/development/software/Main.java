package it.ts.units.development.software;

import it.ts.units.development.software.controller.GameController;
import it.ts.units.development.software.view.ConsoleView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameController controller = new GameController(new Scanner(System.in), new ConsoleView());
        controller.startGame();
    }

}