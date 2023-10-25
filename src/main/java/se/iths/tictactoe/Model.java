package se.iths.tictactoe;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Model {
    private int [][] arr = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private StringProperty nameOfWinner = new SimpleStringProperty();
    private BooleanProperty gameIsOver = new SimpleBooleanProperty();
    private IntegerProperty yourScore = new SimpleIntegerProperty();
    public void buttonClicked(MouseEvent event) {

    }
    public BooleanProperty gameIsOverProperty() { //namnbyte
        return gameIsOver;
    }
    public boolean isGameIsOver() { //namnbyte
        return gameIsOver.get();
    }
    public void setGameIsOver(boolean gameIsOver) { //namnbyte
        this.gameIsOver.set(gameIsOver);
    }

    public StringProperty nameOfWinnerProperty() {
        return nameOfWinner;
    }

    public IntegerProperty yourScoreProperty() {
        return yourScore;
    }
}
