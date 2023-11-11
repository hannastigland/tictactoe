package se.iths.tictactoe;

public class Player {
    private final String player;
    private int playerScore;

    public Player(String player) {
        this.player = player;
        this.playerScore = 0;
    }

    public void setPlayerScore(int points) {
        playerScore = playerScore + points;
    }

    public String getPlayer() {
        return player;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
