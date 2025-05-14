/*
package it.ts.units.development.software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GomokuGUI {
    private static final int GRID_SIZE = 15;
    private JFrame frame;
    private JButton[][] buttons;
    private boolean blackTurn = true; // Start with black

    public GomokuGUI() {
        frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        buttons = new JButton[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(193, 154, 107)); // Default cell color
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (blackTurn) {
                buttons[row][col].setBackground(Color.BLACK);
            } else {
                buttons[row][col].setBackground(Color.WHITE);
            }

            buttons[row][col].setEnabled(false); // Disable button after selection
            blackTurn = !blackTurn; // Switch turns
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GomokuGUI::new);
    }
}


*/

/*package it.ts.units.development.software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GomokuGUI {
    private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 40; // Adjust for visibility
    private static final Color BOARD_COLOR = new Color(193, 154, 107); // Wooden board color
    private static final Color GRID_COLOR = new Color(100, 75, 50); // Dark brown grid lines

    private JFrame frame;
    private StonePanel[][] board;
    private boolean blackTurn = true; // Start with black

    public GomokuGUI() {
        frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        frame.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        board = new StonePanel[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = new StonePanel();
                board[i][j].setBackground(BOARD_COLOR);
                board[i][j].addMouseListener(new StoneClickListener(i, j));
                frame.add(board[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private class StoneClickListener extends MouseAdapter {
        private final int row, col;

        public StoneClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!board[row][col].hasStone()) {
                board[row][col].setStone(blackTurn ? Color.BLACK : Color.WHITE);
                blackTurn = !blackTurn; // Switch turn
            }
        }
    }

    private static class StonePanel extends JPanel {
        private Color stoneColor = null;

        public void setStone(Color color) {
            this.stoneColor = color;
            repaint();
        }

        public boolean hasStone() {
            return stoneColor != null;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw grid lines
            g.setColor(GRID_COLOR);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            // Draw stone if placed
            if (stoneColor != null) {
                int padding = 6; // Padding to fit inside cell
                g.setColor(stoneColor);
                g.fillOval(padding, padding, getWidth() - 2 * padding, getHeight() - 2 * padding);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GomokuGUI::new);
    }
}
*/
/*
package it.ts.units.development.software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GomokuGUI extends JPanel {
    private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 40; // Size of each grid square
    private static final int BOARD_PADDING = CELL_SIZE / 2; // Padding to center grid
    private static final int INNER_GRID_SIZE = GRID_SIZE - 1; // Exclude outer border
    private static final Color BOARD_COLOR = new Color(193, 154, 107); // Wooden color
    private static final Color GRID_COLOR = new Color(100, 75, 50); // Dark brown grid
    private boolean blackTurn = true;
    private final boolean[][] stonePlaced = new boolean[GRID_SIZE][GRID_SIZE];
    private final Color[][] stoneColors = new Color[GRID_SIZE][GRID_SIZE];

    public GomokuGUI() {
        setPreferredSize(new Dimension(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE));
        setBackground(BOARD_COLOR);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = (e.getX() - BOARD_PADDING + CELL_SIZE / 2) / CELL_SIZE;
                int row = (e.getY() - BOARD_PADDING + CELL_SIZE / 2) / CELL_SIZE;

                // Ensure the click is within valid intersections (excluding the outermost edges)
                if (row > 0 && row < INNER_GRID_SIZE && col > 0 && col < INNER_GRID_SIZE && !stonePlaced[row][col]) {
                    stonePlaced[row][col] = true;
                    stoneColors[row][col] = blackTurn ? Color.BLACK : Color.WHITE;
                    blackTurn = !blackTurn;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid lines (excluding outermost edges)
        g2.setColor(GRID_COLOR);
        for (int i = 1; i < INNER_GRID_SIZE; i++) { // Skip 0 and GRID_SIZE - 1
            int x = BOARD_PADDING + i * CELL_SIZE;
            int y = BOARD_PADDING + i * CELL_SIZE;
            g2.drawLine(BOARD_PADDING, y, BOARD_PADDING + (INNER_GRID_SIZE - 1) * CELL_SIZE, y); // Horizontal
            g2.drawLine(x, BOARD_PADDING, x, BOARD_PADDING + (INNER_GRID_SIZE - 1) * CELL_SIZE); // Vertical
        }

        // Draw stones at intersections
        for (int i = 1; i < INNER_GRID_SIZE; i++) {
            for (int j = 1; j < INNER_GRID_SIZE; j++) {
                if (stonePlaced[i][j]) {
                    g2.setColor(stoneColors[i][j]);
                    int stoneSize = CELL_SIZE - 10; // Stones smaller than a full cell
                    int x = BOARD_PADDING + j * CELL_SIZE - stoneSize / 2;
                    int y = BOARD_PADDING + i * CELL_SIZE - stoneSize / 2;
                    g2.fillOval(x, y, stoneSize, stoneSize);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        GomokuGUI board = new GomokuGUI();
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
*/
package it.ts.units.development.software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GomokuGUI extends JPanel {
    private static final int GRID_SIZE = 15;  // 15x15 intersections
    private static final int CELL_SIZE = 40;  // Distance between intersections
    private static final int BOARD_PADDING = CELL_SIZE;  // Padding around the board
    private static final Color BOARD_COLOR = new Color(193, 154, 107);
    private static final Color GRID_COLOR = new Color(100, 75, 50);

    private boolean blackTurn = true;
    private final int[][] board = new int[GRID_SIZE][GRID_SIZE]; // 0 = empty, 1 = black, 2 = white

    public GomokuGUI() {
        setPreferredSize(new Dimension((GRID_SIZE - 1) * CELL_SIZE + 2 * BOARD_PADDING,
                (GRID_SIZE - 1) * CELL_SIZE + 2 * BOARD_PADDING));
        setBackground(BOARD_COLOR);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = (e.getX() - BOARD_PADDING + CELL_SIZE / 2) / CELL_SIZE;
                int row = (e.getY() - BOARD_PADDING + CELL_SIZE / 2) / CELL_SIZE;

                if (row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE && board[row][col] == 0) {
                    board[row][col] = blackTurn ? 1 : 2;
                    blackTurn = !blackTurn;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid lines (15x15 intersections, meaning 15 vertical and 15 horizontal lines)
        g2.setColor(GRID_COLOR);
        for (int i = 0; i < GRID_SIZE; i++) {
            int x = BOARD_PADDING + i * CELL_SIZE;
            int y = BOARD_PADDING + i * CELL_SIZE;
            g2.drawLine(BOARD_PADDING, y, BOARD_PADDING + (GRID_SIZE - 1) * CELL_SIZE, y);
            g2.drawLine(x, BOARD_PADDING, x, BOARD_PADDING + (GRID_SIZE - 1) * CELL_SIZE);
        }

        // Draw stones on valid intersections
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] != 0) {
                    g2.setColor(board[i][j] == 1 ? Color.BLACK : Color.WHITE);
                    int stoneSize = CELL_SIZE - 10;
                    int x = BOARD_PADDING + j * CELL_SIZE - stoneSize / 2;
                    int y = BOARD_PADDING + i * CELL_SIZE - stoneSize / 2;
                    g2.fillOval(x, y, stoneSize, stoneSize);
                }
            }
        }
    }

    public int[][] getBoardMatrix() {
        return board;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        GomokuGUI board = new GomokuGUI();
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

