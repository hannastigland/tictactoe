package se.iths.tictactoe;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class Model {

    private StringProperty nameOfWinner = new SimpleStringProperty();
    private BooleanProperty gameIsOver = new SimpleBooleanProperty();
    private IntegerProperty yourScore = new SimpleIntegerProperty();
    private IntegerProperty turn = new SimpleIntegerProperty();
    private String [][] board;
    public void buttonClicked(MouseEvent event) {

    }
    public Model () {
        board = new String [3][3];
    }
    public String [][] getBoard() {
        return board;
    }
    public void setSymbol(Button button) {
        if (turn.get() == 0) {
            button.setText("X");
            button.setDisable(true);
            nextTurn();
        } else {
            button.setText("O");
            button.setDisable(true);
            nextTurn();
        }
    }
    public int getTurn() {
        return turn.get();
    }
    public IntegerProperty turnProperty() {
        return turn;
    }
    public void nextTurn() {
        if (turn.get() == 0)
            turn.set(1);
        else
            turn.set(0);
    }
    public String getNameOfWinner() {
        return nameOfWinner.get();
    }
    public StringProperty nameOfWinnerProperty() {
        return nameOfWinner;
    }
    public void setNameOfWinner(String nameOfWinner) {
        this.nameOfWinner.set(nameOfWinner);
    }
    public BooleanProperty gameIsOverProperty() { //namnbyte
        return gameIsOver;
    }
    public void gameOver(List<Button> buttons) {
        String winningRow = "";
        for (int i = 0; i < 8; i++) {
            winningRow = switch (i) {
                case 0 -> buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText();
                case 1 -> buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText();
                case 2 -> buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText();
                case 3 -> buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText();
                case 4 -> buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText();
                case 5 -> buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText();
                case 6 -> buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText();
                case 7 -> buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText();
                default -> "";
            };
            if (winningRow.equals("XXX")) {
                setNameOfWinner("Winner: X won!");
                buttons.forEach(e -> e.setDisable(true));
            } else if (winningRow.equals("OOO")) {
                setNameOfWinner("Winner: O won!");
                buttons.forEach(e -> e.setDisable(true));
            }
        }
    }
    public void resetWinnerText(List <Button> buttons) {
        this.nameOfWinner.set("Winner: ");
        buttons.forEach(this::resetButton);
    }

    private void resetButton(Button e) {
        e.setText("");
        e.setDisable(false);
        this.turn.set(0);
    }
    public void setGameIsOver(boolean gameIsOver) { //namnbyte
        this.gameIsOver.set(gameIsOver);
    }

    public IntegerProperty yourScoreProperty() {
        return yourScore;
    }

}
