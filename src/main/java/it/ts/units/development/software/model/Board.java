package it.ts.units.development.software.model;

public class Board {
    public static final int DEFAULT_SIZE = 15;
    public static final char EMPTY_CELL = '.';

    private final int size;
    private final char[][] grid;

    public Board() {
        this.size = DEFAULT_SIZE;
        this.grid = new char[size][size];
    }

    public Board(int size) {
        if (size < 5) {
            throw new IllegalArgumentException("Board size must be at least 5");
        }
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = EMPTY_CELL;
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        validatePosition(row, col);
        return grid[row][col] == EMPTY_CELL;
    }

    public void placeMove(int row, int col, char symbol) {
        validatePosition(row, col);
        if (!isCellEmpty(row, col)) {
            throw new IllegalStateException("Cell already occupied");
        }
        grid[row][col] = symbol;
    }

    public char getCell(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();
        for (int row = 0; row < size; row++) {
            System.out.printf("%2d ", row);
            for (int col = 0; col < size; col++) {
                System.out.print(" " + grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Invalid board coordinates: (" + row + "," + col + ")");
        }
    }
}
