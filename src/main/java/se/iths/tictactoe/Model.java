package se.iths.tictactoe;

import java.util.Objects;

public class Model {
    public String[][] gameBoard;
    public Player player1 = new Player("X");
    public Player aiPlayer = new Player("O");
    public String currentPlayer = player1.getPlayer();

    public Model() {
        initializeGameBoard();
    }

    public void initializeGameBoard() {
        gameBoard = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = " ";
            }
        }
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void placeSymbolAt(int row, int column, String symbol) {
        if (isMoveValid(row, column)) {
            gameBoard[row][column] = symbol;
        }
    }

    public boolean isMoveValid(int row, int column) {
        return gameBoard[row][column].equals(" ");
    }

    public String getSymbolAt(int row, int column) {
        return gameBoard[row][column];
    }

    public boolean checkWinnerOrDraw() {
        if (whoIsWinner(player1.getPlayer())) {
            player1.updatePlayerScore(1);
            return true;
        } else if (whoIsWinner(aiPlayer.getPlayer())) {
            aiPlayer.updatePlayerScore(1);
            return true;
        } else return isBoardFull();
    }

    public boolean isDraw() {
        return isBoardFull() && !whoIsWinner(player1.getPlayer()) && !whoIsWinner(aiPlayer.getPlayer());
    }

    public boolean whoIsWinner(String playerSymbol) {
        for (int i = 0; i < 3; i++) {
            if (playerSymbol.equals(gameBoard[i][0]) && playerSymbol.equals(gameBoard[i][1]) && playerSymbol.equals(gameBoard[i][2]))
                return true;
            if (playerSymbol.equals(gameBoard[0][i]) && playerSymbol.equals(gameBoard[1][i]) && playerSymbol.equals(gameBoard[2][i]))
                return true;
        }
        if (playerSymbol.equals(gameBoard[0][0]) && playerSymbol.equals(gameBoard[1][1]) && playerSymbol.equals(gameBoard[2][2]))
            return true;

        return playerSymbol.equals(gameBoard[0][2]) && playerSymbol.equals(gameBoard[1][1]) && playerSymbol.equals(gameBoard[2][0]);
    }

    boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!"X".equals(gameBoard[i][j]) && !"O".equals(gameBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchCurrentPlayer() {
        currentPlayer = (Objects.equals(currentPlayer, player1.getPlayer())) ? aiPlayer.getPlayer() : player1.getPlayer();
    }
}