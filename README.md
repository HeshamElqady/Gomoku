# Gomoku – Command Line Game

Gomoku is a classic two-player board game implemented in **Java** for the command line. The objective is simple: be the first player to align five stones horizontally, vertically, or diagonally on the board.

---

## Features

- Two-player game (Black vs White)
- Console-based UI
- Input validation and move management
- Win condition detection (5 in a row)

---

##  Requirements

- **Java 21** or higher
- **Maven** (for building the project)

---

##  Build & Run

To build and run the project:

1. **Run main class via IDE:**


2. **Compile and package the project:**
   ```bash
   mvn clean compile package
   ```

3. **Run the game:**
   ```bash
   java -jar target/Gomoku-1.0.jar
   ```

---

## Gameplay

- The board will be displayed on the terminal.
- Players alternate turns by entering coordinates (e.g., `7 8`).
- The game ends when a player achieves 5 in a row or when the board is full.
- If you chose Renju game logic changes to new rules


---