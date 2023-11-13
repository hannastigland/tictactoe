package se.iths.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeModelTest {

    private Model model;

    @BeforeEach
    public void setup() {
        model = new Model();
    }

    @Test
    public void occupiedButtonCantBeSelected() {
        String[][] board = {
                {"X", "O", " "},
                {" ", "X", "O"},
                {" ", " ", "X"}
        };
        model.setGameBoard(board);
        assertFalse(model.isMoveValid(0, 1));
        assertFalse(model.isMoveValid(1, 2));
    }

    @Test
    public void emptyButtonCanBeSelected() {
        String[][] board = {
                {"X", "O", " "},
                {" ", "X", "O"},
                {" ", " ", "X"}
        };
        model.setGameBoard(board);
        assertTrue(model.isMoveValid(0, 2));
        assertFalse(model.isMoveValid(2, 2));
    }

    @Test
    public void validMovesForBothPlayers() {
        String[][] board = {
                {" ", " ", " "},
                {" ", "X", " "},
                {"O", " ", " "}
        };
        model.setGameBoard(board);
        assertTrue(model.isMoveValid(0, 0));
        assertTrue(model.isMoveValid(2, 1));
        assertFalse(model.isMoveValid(2, 0));
    }

    @Test
    public void playerWins() {
        model.initializeGameBoard();
        model.placeSymbolAt(2, 0, "X");
        model.placeSymbolAt(2, 1, "X");
        model.placeSymbolAt(2, 2, "X");
        model.placeSymbolAt(0, 0, "O");
        model.placeSymbolAt(0, 2, "O");
        model.placeSymbolAt(1, 1, "O");
        assertTrue(model.whoIsWinner("X"));
        assertFalse(model.whoIsWinner("O"));
    }

    @Test
    public void playerDoesNotWin() {
        model.initializeGameBoard();
        model.placeSymbolAt(0, 2, "O");
        model.placeSymbolAt(1, 1, "O");
        model.placeSymbolAt(2, 0, "O");
        model.placeSymbolAt(1, 2, "X");
        model.placeSymbolAt(2, 1, "X");
        model.placeSymbolAt(2, 2, "X");
        assertFalse(model.whoIsWinner("X"));
        assertTrue(model.whoIsWinner("O"));
    }

    @Test
    public void aiWins() {
        model.initializeGameBoard();
        model.placeSymbolAt(1, 0, "O");
        model.placeSymbolAt(1, 1, "O");
        model.placeSymbolAt(1, 2, "O");
        model.placeSymbolAt(0, 1, "X");
        model.placeSymbolAt(0, 2, "X");
        model.placeSymbolAt(2, 0, "X");
        assertFalse(model.whoIsWinner("X"));
        assertTrue(model.whoIsWinner("O"));
    }

    @Test
    public void aiDoesNotWin() {
        model.initializeGameBoard();
        model.placeSymbolAt(0, 0, "O");
        model.placeSymbolAt(0, 1, "O");
        model.placeSymbolAt(1, 1, "O");
        model.placeSymbolAt(2, 0, "X");
        model.placeSymbolAt(2, 1, "X");
        model.placeSymbolAt(2, 2, "X");
        assertFalse(model.whoIsWinner("O"));
        assertTrue(model.whoIsWinner("X"));
    }

    @Test
    public void isDraw () {
        String[][] board = {
                {"X", "O", "X"},
                {"X", "X", "O"},
                {"O", "X", "O"}
        };
        model.setGameBoard(board);
        assertTrue(model.isDraw());
        assertFalse(model.whoIsWinner("X"));
        assertFalse(model.whoIsWinner("O"));
    }

    @Test
    public void gameEndsWithWinnerOrDraw() {
        String[][] winBoard = {
                {"X", "O", " "},
                {"X", "O", " "},
                {"X", " ", " "}
        };
        model.setGameBoard(winBoard);
        assertTrue(model.checkWinnerOrDraw());
        assertTrue(model.whoIsWinner("X"));
        assertFalse(model.whoIsWinner("O"));
        assertFalse(model.isDraw());

        model.initializeGameBoard();

        String[][] drawBoard = {
                {"X", "O", "X"},
                {"X", "O", "O"},
                {"O", "X", "X"}
        };
        model.setGameBoard(drawBoard);
        assertTrue(model.isDraw());
        assertFalse(model.whoIsWinner("O"));
        assertFalse(model.whoIsWinner("X"));
    }

    @Test
    public void switchCurrentPlayer() {
        assertEquals("X", model.currentPlayer);
        model.switchCurrentPlayer();
        assertEquals("O", model.currentPlayer);
        model.switchCurrentPlayer();
        assertEquals("X", model.currentPlayer);
    }
}