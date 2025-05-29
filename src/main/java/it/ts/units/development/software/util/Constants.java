package it.ts.units.development.software.util;

public class Constants {
    public static final String WELCOME_TO_THE_GAME = "WELCOME TO THE GAME, INSERT G FOR NORMAL GOMOKU AND R FOR RENJU GOMOKU";
    public static final String INITIALIZE_GAME = "WELCOME TO THE GAME, PLEASE SELECT ONE OF THESE BOARD SIZES (15,19)";
    public static final String INITIALIZE_GOMOKU_GAME = "WELCOME TO GOMOKU\nRULES ARE SIMPLE, FIRST TO CONNECT 5 OR MORE PEICES WINS";
    public static final String INITIALIZE_RENJU_GOMOKU_GAME = "WELCOME TO RENJU GOMOKU\nSPECIAL RULES:\n\tBLACK CAN'T PLACE AN OVERLINE\n\tBLACK CAN'T PLAY A DOUBLE 3 OR A DOUBLE 4\n FOR MORE HELP INSERT H, TO START THE GAME INSERT S";
    public static final String HELP_RENJU_GOMOKU_GAME = "BLACK CAN'T PLACE A MOVE IN AN INTERSECTION THAT CONNECTS DOUBLE THREE OR DOUBLE FOUR AS A RESULT EX: X X _\n" +
                                                        "                                                                                                          X\n" +
                                                        "                                                                                                          X ";
    public static final String PLAYER_X = "INSERT THE NAME OF THE PLAYER WITH X (BLACK PIECE)";
    public static final String PLAYER_O = "INSERT THE NAME OF THE PLAYER WITH O (WHITE PIECE)";
    public static final String PLACE_PIECE = "PLAY A MOVE IN THIS WAY -> R C";
    public static final String PLAYER_TO_MOVE = "PLAYER %s (%c), MAKE YOUR MOVE:\n";

    public static final String ERROR_CELL_OCCUPIED = "CELL IS ALREADY OCCUPIED, PLACE YOUR PEICE ON AN EMPTY CELL";
    public static final String ERROR_INVALID_MOVE = "INVALID MOVE, RESPECT THE BORDERS";
    public static final String ERROR_OVER_LINE = "INVALID MOVE, BLACK LOSES FOR OVERLINE";
    public static final String ERROR_DOUBLE_THREE = "INVALID MOVE, BLACK LOSES FOR DOUBLE THREE";
    public static final String ERROR_DOUBLE_FOUR = "INVALID MOVE, BLACK LOSES FOR DOUBLE FOUR";
    public static final String ERROR_INVALID_INPUT = "INVALID INPUT";

    public static final String GAME_ENDED_BY_DRAW = "GAME ENDED BY A DRAW";
    public static final String GAME_ENDED_PLAYER_WINS = "GAME ENDED %s WINS";

}
