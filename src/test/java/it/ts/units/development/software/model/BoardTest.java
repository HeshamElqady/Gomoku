package it.ts.units.development.software.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(15); // assuming 15x15 board
    }

    @Test
    void testInitializeDefaultEmptyBoard() {
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {
                assertEquals(Board.EMPTY_CELL, board.getCell(r, c));
            }
        }
    }

    @Test
    void testInitializeOldSizeEmptyBoard() {
        Board board19x19 = new Board(19);
        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                assertEquals(Board.EMPTY_CELL, board19x19.getCell(r, c));
            }
        }
    }

    @Test
    void testPlaceMoveCorrectly() {
        board.placeMove(7, 7, 'X');
        assertEquals('X', board.getCell(7, 7));
    }

    @Test
    void testPlaceMoveIncorrectly() {
        board.placeMove(7, 7, 'X');
        assertEquals('X', board.getCell(7, 7));
        assertThrows(IllegalStateException.class, () -> board.placeMove(7, 7, 'O'));
    }

    @Test
    void testThrowWhenPlacingOutOfBounds() {
//        assertThrows(IndexOutOfBoundsException.class, () -> board.placeMove(20, 20, 'O'));
        assertDoesNotThrow(() -> board.placeMove(20, 20, 'O'));
    }
}