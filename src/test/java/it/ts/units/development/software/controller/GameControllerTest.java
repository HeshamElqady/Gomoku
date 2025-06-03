package it.ts.units.development.software.controller;

import it.ts.units.development.software.model.Player;
import it.ts.units.development.software.view.ConsoleView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;
/**
 * <b>N.B: This test was made after development because we didn't predict how game control could have been from the beginning </b>*/
@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private Scanner scannerMock;
    @Mock
    private ConsoleView viewMock;
    @Captor
    ArgumentCaptor<Player> playerCaptor;
    @InjectMocks
    private GameController gameController;

    @Test
    void testGiveBadGameChoice(){
        Mockito.when(scannerMock.nextLine()).thenReturn("F").thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("G");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printInputError();
    }

    @Test
    void testGiveBadRenjuStart(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("t").thenReturn("R").thenReturn("s");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printInputError();
    }

    @Test
    void testGiveBadBoardSize(){
        Mockito.when(scannerMock.nextLine()).thenReturn("13").thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("s");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printInputError();
    }

    @Test
    void testPlaceMoveInOccupiedCell(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("s");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printOccupiedMove();
    }

    @Test
    void testPlaceMoveOutOfBorders(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("s");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(15).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printInvalidMove();
    }

    @Test
    void testNormalGamePlayThroughBlackWins(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("G");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(10)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(9)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

       //ASSERT BLACK WINNING
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p1", winner.getName());
        Assertions.assertEquals('X', winner.getSymbol());
    }

    @Test
    void testNormalGamePlayThroughWhiteWins(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("G");
        Mockito.when(scannerMock.nextInt()).thenReturn(9).thenReturn(9).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(11)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(10)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

       //ASSERT BLACK WINNING
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p2", winner.getName());
        Assertions.assertEquals('O', winner.getSymbol());
    }

    @Test
    void testRenjuGamePlayThroughBlackWinsWithNoInvalidMoves(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("H").thenReturn("S");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printRenjuGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printHelpRenju();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(10)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(9)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

        //ASSERT BLACK WINNING
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p1", winner.getName());
        Assertions.assertEquals('X', winner.getSymbol());
    }

    @Test
    void testRenjuGamePlayThroughBlackLosesWithOverLine(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("H").thenReturn("S");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(3).thenReturn(0).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(5).thenReturn(9).thenReturn(9).thenReturn(0).thenReturn(4);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printRenjuGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printHelpRenju();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(12)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(11)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printOverlineError();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

        //ASSERT BLACK LOSS
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p2", winner.getName());
        Assertions.assertEquals('O', winner.getSymbol());
    }

    @Test
    void testRenjuGamePlayThroughBlackLosesWithDoubleThree(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("H").thenReturn("S");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(12).thenReturn(1).thenReturn(2).thenReturn(2).thenReturn(10).thenReturn(1).thenReturn(0).thenReturn(2);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printRenjuGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printHelpRenju();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(10)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(9)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printDoubleThree();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

        //ASSERT BLACK LOSS
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p2", winner.getName());
        Assertions.assertEquals('O', winner.getSymbol());
    }
    @Test
    void testRenjuGamePlayThroughBlackLosesWithDoubleFour(){
        Mockito.when(scannerMock.nextLine()).thenReturn("15").thenReturn("p1").thenReturn("p2").thenReturn("R").thenReturn("H").thenReturn("S");
        Mockito.when(scannerMock.nextInt()).thenReturn(0).thenReturn(0).thenReturn(13).thenReturn(13).thenReturn(0).thenReturn(1).thenReturn(12).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(12).thenReturn(1).thenReturn(1).thenReturn(3).thenReturn(10).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(3).thenReturn(3).thenReturn(4).thenReturn(6).thenReturn(0).thenReturn(3);
        gameController.startGame();
        Mockito.verify(viewMock,Mockito.times(1)).printWelcomeMessage();
        Mockito.verify(viewMock,Mockito.times(1)).printGameChoice();
        Mockito.verify(viewMock,Mockito.times(1)).printRenjuGomokuWelcomeGame();
        Mockito.verify(viewMock,Mockito.times(1)).printHelpRenju();
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('X');
        Mockito.verify(viewMock,Mockito.times(1)).printPlayerNamePrompt('O');
        Mockito.verify(viewMock,Mockito.times(14)).displayBoard(Mockito.any());
        Mockito.verify(viewMock,Mockito.times(13)).printMoveLogic();
        Mockito.verify(viewMock,Mockito.times(1)).printDoubleFour();
        Mockito.verify(viewMock,Mockito.times(1)).printWinner(playerCaptor.capture());

        //ASSERT BLACK LOSS
        Player winner = playerCaptor.getValue();
        Assertions.assertEquals("p2", winner.getName());
        Assertions.assertEquals('O', winner.getSymbol());
    }

}