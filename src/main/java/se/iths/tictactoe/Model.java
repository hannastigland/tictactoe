package se.iths.tictactoe;

import javafx.beans.property.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model {

    private StringProperty nameOfWinner = new SimpleStringProperty();
    private BooleanProperty gameIsOver = new SimpleBooleanProperty();
    private IntegerProperty yourScore = new SimpleIntegerProperty();
    private IntegerProperty aiScore = new SimpleIntegerProperty();
    private IntegerProperty playerTurn = new SimpleIntegerProperty();
    private int turnTotal; //ändra namn, och på xoTurn
    private boolean isGameOver;
    public void buttonClicked(MouseEvent event) {

    }
    public Model () {
        nameOfWinner.setValue("Winner: ");
        playerTurn.setValue(0);
        turnTotal = 0;
        isGameOver = false;
        aiScore.set(0);
        yourScore.set(0);
    }

    public void setSymbol(Button button) {
        if (playerTurn.get() == 0) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setDisable(true);
        nextTurn();
    }
    public int getPlayerTurn() {
        return playerTurn.get();
    }
    public IntegerProperty playerTurnProperty() {
        return playerTurn;
    }
    public void nextTurn() {
        if (playerTurn.get() == 0)
            playerTurn.set(1);
        else
            playerTurn.set(0);
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
                setNameOfWinner("Winner: You won!");
                disableButtons(buttons);
                onePoint(); //= givePoints
            } else if (winningRow.equals("OOO")) {
                setNameOfWinner("Winner: Computer won!");
                disableButtons(buttons);
                onePoint();
            } else if(turnTotal > 8) {
                setNameOfWinner("Winner: Draw!");
                disableButtons(buttons);
        }
    }
    public void onePoint() {
        String method_name = Thread.currentThread().getStackTrace()[3].getMethodName();
        if (method_name.equals("buttonClicked"))
            yourScore.set(yourScore.get()+1);
        else
            aiScore.set(aiScore.get()+1);
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
        this.playerTurn.set(0);
    }
    public int getTurnTotal() {
        return turnTotal;
    }
    public boolean isGameOver() {
        return isGameOver;
    }
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getYourScore() {
        return yourScore.get();
    }

    public IntegerProperty yourScoreProperty() {
        return yourScore;
    }

    public int getAiScore() {
        return aiScore.get();
    }

    public IntegerProperty aiScoreProperty() {
        return aiScore;
    }
    public void aiTurn(List<Button> buttons) {
        Random random = new Random();
        int selectedButton; //=buttonNumber
        while (true) {
            selectedButton = random.nextInt(9);
            if (playableButton(selectedButton, buttons)) {
                setSymbol(buttons.get(selectedButton));
                gameOver(buttons);
                break;
            }
        }
    }
    private boolean playableButton(int buttonNumber, List <Button> buttons) { //=int index
        return !buttons.get(buttonNumber).isDisabled();
    }
}
