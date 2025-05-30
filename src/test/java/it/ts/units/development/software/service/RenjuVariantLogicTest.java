package it.ts.units.development.software.service;

import it.ts.units.development.software.exception.IllegalDoubleFourException;
import it.ts.units.development.software.exception.IllegalDoubleThreeException;
import it.ts.units.development.software.exception.IllegalOverlineException;
import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RenjuVariantLogicTest {
    private Player player1;
    private Player player2;
    private RenjuVariantLogic renju;

    @BeforeEach
    public void setUp() {
        player1 = new Player("Alice", 'X');
        player2 = new Player("Bob", 'O');
        renju = new RenjuVariantLogic(player1, player2, 15);
    }

    @Test
    void testRenjuVariantWithDoubleThree() {
        assertTrue(renju.makeMove(new Move(7, 0))); // P1
        assertTrue(renju.makeMove(new Move(10, 0))); // P2
        assertTrue(renju.makeMove(new Move(7, 1))); // P1
        assertTrue(renju.makeMove(new Move(1, 0))); // P2
        assertTrue(renju.makeMove(new Move(6, 2))); // P1
        assertTrue(renju.makeMove(new Move(13, 13))); // P2
        assertTrue(renju.makeMove(new Move(5, 2))); // P1
        assertTrue(renju.makeMove(new Move(9, 9))); // P2
        Move forbiddenMove = new Move(7, 2);
        assertThrows(IllegalDoubleThreeException.class, () -> renju.makeMove(forbiddenMove)); // P1
        assertTrue(renju.isGameOver());
    }

    @Test
    void testRenjuVariantWithDoubleFours() {
        assertTrue(renju.makeMove(new Move(7, 0))); // P1
        assertTrue(renju.makeMove(new Move(10, 0))); // P2
        assertTrue(renju.makeMove(new Move(7, 1))); // P1
        assertTrue(renju.makeMove(new Move(1, 0))); // P2
        assertTrue(renju.makeMove(new Move(7, 2))); // P1
        assertTrue(renju.makeMove(new Move(2, 0))); // P2
        assertTrue(renju.makeMove(new Move(6, 3))); // P1
        assertTrue(renju.makeMove(new Move(13, 13))); // P2
        assertTrue(renju.makeMove(new Move(5, 3))); // P1
        assertTrue(renju.makeMove(new Move(9, 9))); // P2
        assertTrue(renju.makeMove(new Move(4, 3))); // P1
        assertTrue(renju.makeMove(new Move(11, 9))); // P2
        Move forbiddenMove = new Move(7, 3);
        assertThrows(IllegalDoubleFourException.class, () -> renju.makeMove(forbiddenMove)); // P1
        assertTrue(renju.isGameOver());
    }


    @Test
    void testRenjuVariantWithOverLine() {
        assertTrue(renju.makeMove(new Move(7, 0))); // P1
        assertTrue(renju.makeMove(new Move(10, 0))); // P2
        assertTrue(renju.makeMove(new Move(7, 1))); // P1
        assertTrue(renju.makeMove(new Move(1, 0))); // P2
        assertTrue(renju.makeMove(new Move(7, 2))); // P1
        assertTrue(renju.makeMove(new Move(2, 0))); // P2
        assertTrue(renju.makeMove(new Move(6, 3))); // P1
        assertTrue(renju.makeMove(new Move(13, 13))); // P2
        assertTrue(renju.makeMove(new Move(7, 4))); // P1
        assertTrue(renju.makeMove(new Move(9, 9))); // P2
        assertTrue(renju.makeMove(new Move(7, 5))); // P1
        assertTrue(renju.makeMove(new Move(11, 9))); // P2
        Move forbiddenMove = new Move(7, 3);
        assertThrows(IllegalOverlineException.class, () -> renju.makeMove(forbiddenMove)); // P1
        assertTrue(renju.isGameOver());

    }

    @Test
    public void testMakeValidMove() {
        Move move = new Move(7, 7);
        assertTrue(renju.makeMove(move));
        assertEquals('X', renju.getBoard().getCell(7, 7));
    }

    @Test
    public void testMakeMoveInOccupiedCell() {
        Move move = new Move(5, 5);
        assertTrue(renju.makeMove(move)); // Player 1
        assertFalse(renju.makeMove(move)); // Player 2
    }

    @Test
    public void testWinConditionHorizontal() {
        for (int i = 0; i < 5; i++) {
            assertTrue(renju.makeMove(new Move(7, i))); // P1
            if (i < 4) {
                assertTrue(renju.makeMove(new Move(8, i))); // P2
            }
        }
        assertTrue(renju.isGameOver());
    }

    @Test
    public void testSwitchTurn() {
        assertEquals(player1, renju.getCurrentPlayer());
        renju.makeMove(new Move(0, 0));
        assertEquals(player2, renju.getCurrentPlayer());
    }

    @Test
    public void testGameEndsAfterWin() {
        for (int i = 0; i < 5; i++) {
            renju.makeMove(new Move(i, i));  // P1
            if (i < 4) {
                renju.makeMove(new Move(i, i + 1)); // P2
            }
        }
        assertTrue(renju.isGameOver());

        // Try making another move
        assertFalse(renju.makeMove(new Move(0, 14)));
    }
}