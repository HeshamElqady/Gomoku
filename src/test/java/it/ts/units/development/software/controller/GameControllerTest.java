//package it.ts.units.development.software.controller;
//
//import it.ts.units.development.software.view.ConsoleView;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Scanner;
//
//@ExtendWith(MockitoExtension.class)
//class GameControllerTest {
//    @Mock
//    private Scanner mockScanner;
//    @Mock
//    private ConsoleView consoleView;
//
//    @InjectMocks
//    private GameController gameController;
//
//
//    @Test
//    void initGameWithWrongBoardSize() {
//        Mockito.when(mockScanner.nextLine()).thenReturn("20").thenReturn("15").thenReturn("G");
//        gameController = new GameController(mockScanner, consoleView);
//        Mockito.verify(consoleView, Mockito.times(1)).printInputError();
//    }
//
//
//}