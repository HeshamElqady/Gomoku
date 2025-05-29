package it.ts.units.development.software.service;

import it.ts.units.development.software.model.Move;
import it.ts.units.development.software.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private Player player1;
    private Player player2;
    private GameLogic game;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        game = new TestGameLogic(player1, player2, 15);
    }

    @Test
    void testInitialCurrentPlayerIsPlayer1() {
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void testMakeValidMove() {
        Move move = new Move(0, 0);
        assertTrue(game.makeMove(move));
        assertEquals('X', game.getBoard().getCell(0, 0));
    }

    @Test
    void testSwitchTurnsAfterValidMove() {
        Move move = new Move(0, 0);
        game.makeMove(move);
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    void testCannotMoveToOccupiedCell() {
        game.makeMove(new Move(0, 0));
        assertFalse(game.makeMove(new Move(0, 0)));
    }

    @Test
    void testGameOverPreventsFurtherMoves() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(game.makeMove(new Move(i, j)));
            }
        }
        assertFalse(game.makeMove(new Move(0, 14)));
        assertTrue(game.isGameOver());
    }

    @Test
    void testGameEndsInDraw() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(game.makeMove(new Move(i, j)));
            }
        }
        assertFalse(game.makeMove(new Move(0, 14)));
        assertTrue(game.isDraw());
    }

    @Test
    void testForbiddenMoveTriggersGameOver() {
        GameLogic forbiddenGame = new GameLogic(player1, player2, 15) {
            @Override
            public boolean checkWin(int row, int col, char symbol) {
                return false;
            }

            @Override
            public boolean isForbiddenMove(Move move) {
                return move.getRow() == 1 && move.getCol() == 1;
            }
        };

        forbiddenGame.makeMove(new Move(0, 0));
        forbiddenGame.makeMove(new Move(1, 1));  // Forbidden move
        assertTrue(forbiddenGame.isGameOver());
    }

    @Test
    void testExceptionInForbiddenMoveHandling() {
        GameLogic throwingGame = new GameLogic(player1, player2, 15) {
            @Override
            public boolean checkWin(int row, int col, char symbol) {
                return false;
            }

            @Override
            public boolean isForbiddenMove(Move move) {
                throw new RuntimeException("Forbidden move check failed");
            }
        };

        assertThrows(RuntimeException.class, () ->
                throwingGame.makeMove(new Move(1, 1))
        );
        assertTrue(throwingGame.isGameOver());
    }

    public static class TestGameLogic extends GameLogic {

        public TestGameLogic(Player player1, Player player2, int boardSize) {
            super(player1, player2, boardSize);
        }

        @Override
        public boolean checkWin(int row, int col, char symbol) {
            return false;
        }

        @Override
        public boolean isForbiddenMove(Move move) {
            return false;
        }
    }
}
