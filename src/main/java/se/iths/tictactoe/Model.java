package se.iths.tictactoe;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;

public class Model {

    private StringProperty nameOfWinner = new SimpleStringProperty();
    private BooleanProperty gameIsOver = new SimpleBooleanProperty();
    private IntegerProperty yourScore = new SimpleIntegerProperty();
    private IntegerProperty xoTurn = new SimpleIntegerProperty();
    private int turnTotal; //ändra namn, och på xoTurn
    private boolean isGameOver;
    public void buttonClicked(MouseEvent event) {

    }
    public Model () {
        nameOfWinner.setValue("Winner: ");
        xoTurn.setValue(0);
        turnTotal = 0;
        isGameOver = false;
    }

    public void setSymbol(Button button) {
        if (xoTurn.get() == 0) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setDisable(true);
        nextTurn();
    }
    public int getXoTurn() {
        return xoTurn.get();
    }
    public IntegerProperty xoTurnProperty() {
        return xoTurn;
    }
    public void nextTurn() {
        if (xoTurn.get() == 0)
            xoTurn.set(1);
        else
            xoTurn.set(0);
        turnTotal++;
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
    public BooleanProperty gameIsOverProperty() { //namnbyte, ta bort?
        return gameIsOver;
    }
    public void gameOver(List<Button> buttons) {
        String[] winningRows = {
            buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText(),
                buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText(),
                buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText(),
                buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText(),
                buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText(),
                buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText(),
                buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText(),
                buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText()
            };
            String winningRow = Arrays.stream(winningRows).filter(w -> w.equals("XXX") ||
                    w.equals("OOO")).findFirst().orElse("");
            if (winningRow.equals("XXX")) {
                setNameOfWinner("Winner: X won!");
                disableButtons(buttons);
            } else if (winningRow.equals("OOO")) {
                setNameOfWinner("Winner: O won!");
                disableButtons(buttons);
            } else if(turnTotal > 8) {
                setNameOfWinner("Winner: Draw!");
                disableButtons(buttons);
        }
    }

    private void disableButtons(List <Button> buttons) {
        buttons.forEach(e -> e.setDisable(true));
        this.isGameOver = true;
    }
    public void resetWinnerText(List <Button> buttons) {
        this.nameOfWinner.set("Winner: ");
        buttons.forEach(this::resetButton);
        this.turnTotal = 0;
        this.isGameOver = false;
    }

    private void resetButton(Button button) {
        button.setText("");
        button.setDisable(false);
        this.xoTurn.set(0);
    }
    public int getTurnTotal() {
        return turnTotal;
    }
    public void setTurnTotal(int turnTotal) {
        this.turnTotal = turnTotal;
    }
    public boolean isGameOver() {
        return isGameOver;
    }
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public IntegerProperty yourScoreProperty() {
        return yourScore;
    }


}
