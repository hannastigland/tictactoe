package se.iths.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    public Label yourScore, nameOfWinner;
    @FXML
    public GridPane gridPane;
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, resetButton;

    private List<Button> buttons;

    private Model model = new Model();

    public Model getModel() {
        return model;
    }

    public void initialize() {
        gridPane.disableProperty().bind(model.gameIsOverProperty());
        nameOfWinner.textProperty().bind(model.nameOfWinnerProperty());
        yourScore.textProperty().bind(model.yourScoreProperty().asString());
        buttons = Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9);
        buttons.forEach(b -> b.setFocusTraversable(false));
    }

    public void buttonClicked(MouseEvent mouseEvent) {
        model.setSymbol((Button) mouseEvent.getSource());
        model.gameOver(buttons);
        if (!model.isGameOver())
            aiTurn();
    }

    public void resetButtonClicked (){
            model.resetWinnerText(buttons);
            model.getPlayerTurn();
        }

    public void aiTurn() { //flytta till model?
        Random random = new Random();
        int selectedButton; //=buttonNumber
        while (true) {
            selectedButton = random.nextInt(9);
            if (playableButton(selectedButton)) {
                model.setSymbol(buttons.get(selectedButton));
                model.gameOver(buttons);
                break;
            }
        }
    }
    private boolean playableButton(int buttonNumber) { //=int index
        return !buttons.get(buttonNumber).isDisabled();
    }
}
