package se.iths.tictactoe;

public class Player {
    private final String player;
    private int score;

    public Player(String player) {
        this.player = player;
        this.score = 0;
    }

    public void updatePlayerScore(int points) {
        score += points;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }
}
