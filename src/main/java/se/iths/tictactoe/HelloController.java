package se.iths.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class HelloController {

    @FXML
    public Label yourScore;
    public Label welcomeText;
    public TextField textField;
    @FXML
    public TilePane pane;
    @FXML
    public Button b1;
    @FXML
    public Button b2;
    @FXML
    public Button b3;
    @FXML
    public Button b4;
    @FXML
    public Button b5;
    @FXML
    public Button b6;
    @FXML
    public Button b7;
    @FXML
    public Button b8;
    @FXML
    public Button b9;

    @FXML
    private Model model = new Model();
    public Model getModel() {
        return model;
    }
    public void buttonClicked(MouseEvent mouseEvent) {
    }
}
