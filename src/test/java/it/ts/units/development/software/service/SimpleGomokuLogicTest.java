package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGomokuLogicTest {

    private SimpleGomokuLogic gameLogic;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Alice", 'X');
        player2 = new Player("Bob", 'O');
        gameLogic = new SimpleGomokuLogic(player1, player2, 15);
    }


    @Test
    public void testWinConditionHorizontal() {
        for (int i = 0; i < 5; i++) {
            assertTrue(gameLogic.makeMove(new Move(7, i))); // P1
            if (i < 4) {
                assertTrue(gameLogic.makeMove(new Move(8, i))); // P2
            }
        }
        assertTrue(gameLogic.isGameOver());
    }

}
