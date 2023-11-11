package se.iths.tictactoe;

import java.util.Objects;

public class Model {
    public String[][] gameBoard;
    public Player player1 = new Player("x");
    public Player aiPlayer = new Player("o");
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

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setABoardPosition(int row, int column, String value) {
        if (isValidMove(row, column)) { //namnbyte x2 + value
            gameBoard[row][column] = value;
        }
    }

    public boolean isValidMove(int row, int column) {
        return gameBoard[row][column].equals(" ");//byt namn
    }

    public String getABoardPosition(int row, int column) {
        return gameBoard[row][column];//byt namn
    }

    public boolean checkWinner() {
        if (hasWon(player1.getPlayer())) {
            player1.setPlayerScore(1);
            return true;
        } else if (hasWon(aiPlayer.getPlayer())) {
            aiPlayer.setPlayerScore(1);
            return true;
        } else return isBoardFull();
    }

    public boolean hasWon(String player) { //byt namn
        for (int i = 0; i < 3; i++) {
            if (player.equals(gameBoard[i][0]) && player.equals(gameBoard[i][1]) && player.equals(gameBoard[i][2]))
                return true;
            if (player.equals(gameBoard[0][i]) && player.equals(gameBoard[1][i]) && player.equals(gameBoard[2][i]))
                return true;
        }
        if (player.equals(gameBoard[0][0]) && player.equals(gameBoard[1][1]) && player.equals(gameBoard[2][2]))
            return true;

        if (player.equals(gameBoard[0][2]) && player.equals(gameBoard[1][1]) && player.equals(gameBoard[2][0]))
            return true;

        return false;
    }

    boolean isBoardFull() { //bytnamn
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!"x".equals(gameBoard[i][j]) && !"o".equals(gameBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void toggleCurrentPlayer() { //bytnamn
        currentPlayer = (Objects.equals(currentPlayer, player1.getPlayer())) ? aiPlayer.getPlayer() : player1.getPlayer();
    }
}