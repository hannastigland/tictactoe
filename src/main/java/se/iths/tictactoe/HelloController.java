package se.iths.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;

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
            model.aiTurn(buttons);
    }

    public void resetButtonClicked() {
        model.resetWinnerText(buttons);
        model.getPlayerTurn();
    }
}
