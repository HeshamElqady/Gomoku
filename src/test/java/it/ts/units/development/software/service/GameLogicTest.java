package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private GameLogic gameLogic;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Alice", 'X');
        player2 = new Player("Bob", 'O');
        gameLogic = new GameLogic(player1, player2, 15) {
            @Override
            public boolean isForbiddenMove(Move move) {
                return false;
            }
        };
    }

    @Test
    public void testMakeValidMove() {
        Move move = new Move(7, 7);
        assertTrue(gameLogic.makeMove(move));
        assertEquals('X', gameLogic.getBoard().getCell(7, 7));
    }

    @Test
    public void testMakeMoveInOccupiedCell() {
        Move move = new Move(5, 5);
        assertTrue(gameLogic.makeMove(move)); // Player 1
        assertFalse(gameLogic.makeMove(move)); // Player 2
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

    @Test
    public void testSwitchTurn() {
        assertEquals(player1, gameLogic.getCurrentPlayer());
        gameLogic.makeMove(new Move(0, 0));
        assertEquals(player2, gameLogic.getCurrentPlayer());
    }

    @Test
    public void testGameEndsAfterWin() {
        for (int i = 0; i < 5; i++) {
            gameLogic.makeMove(new Move(i, i));  // P1
            if (i < 4) {
                gameLogic.makeMove(new Move(i, i + 1)); // P2
            }
        }
        assertTrue(gameLogic.isGameOver());

        // Try making another move
        assertFalse(gameLogic.makeMove(new Move(0, 14)));
    }
}
