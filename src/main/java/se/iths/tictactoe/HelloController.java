package se.iths.tictactoe;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    public Label yourScore, nameOfWinner;
    @FXML
    public GridPane gridPane;
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, resetButton;

    private Model model = new Model();
    public Model getModel() {
        return model;
    }

    public void initialize() {
        gridPane.disableProperty().bind(model.gameIsOverProperty());
        nameOfWinner.textProperty().bind(model.nameOfWinnerProperty());
        yourScore.textProperty().bind(model.yourScoreProperty().asString());
    }
    public void buttonClicked(MouseEvent mouseEvent) {

    }

    public void resetButtonClicked(MouseEvent mouseEvent) {
    }
}
