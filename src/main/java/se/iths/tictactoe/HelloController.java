package se.iths.tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HelloController {
    private final Model model = new Model();
    public Label player, aiPlayer;
    public Button resetButton, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    public Button[] buttons;
    private boolean gameOver;

    public void initialize() {
        buttons = new Button[] {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        startGame();
    }

    private void startGame() {
        gameOver = false;
        model.currentPlayer = model.player1.getPlayer();
        updateButtonLabels();
    }

    public void resetButtonClicked() {
        model.initializeGameBoard();
        startGame();
    }

    private void updateButtonLabels() { //byt namn
        for (Button button : buttons) {
            Integer rowIndex = GridPane.getRowIndex(button);
            Integer columnIndex = GridPane.getColumnIndex(button);
            button.setText(model.getABoardPosition(rowIndex, columnIndex));
        }
    }
    public void buttonClicked(MouseEvent mouseEvent) {
        if ("x".equals(model.currentPlayer)&& !gameOver) {
            playerTurn(mouseEvent);
        }
    }

    private void playerTurn(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        if (source instanceof Button clickedButton) {
            updateGame(clickedButton);
            aiTurn();
        }
    }

    public void updateGame(Button button) { //byt namn
        Integer rowIndex = GridPane.getRowIndex(button);
        Integer columnIndex = GridPane.getColumnIndex(button); //columnindexindex?
        if (model.isValidMove(rowIndex, columnIndex)) {
            button.setText(model.currentPlayer);
            updateModel(rowIndex, columnIndex);
            if (model.checkWinner()) {
                updateScoreLabels();
                gameOver = true;
            } else {
                model.toggleCurrentPlayer();
            }
        }
    }

    private void updateModel(int rowIndex, int columnIndex) {
        model.setABoardPosition(rowIndex, columnIndex, model.currentPlayer);
    }

    private void updateScoreLabels() { //bytnamn
        int playerScore = model.player1.getPlayerScore();
        int aiScore = model.aiPlayer.getPlayerScore();
        player.setText("Your score: " + (playerScore));
        aiPlayer.setText("Computer score: " + (aiScore));
    }

    public void aiTurn() { //byta namn på allt nere
        if ("o".equals(model.currentPlayer) && !gameOver) {
            List<Button> emptyPositions = filterForEmptyPositions();
            if (!emptyPositions.isEmpty()) {
                int selected = randomPosition(emptyPositions);
                Button button = emptyPositions.get(selected);
                updateGame(button);
            }
        }
    }

    private static int randomPosition(List<Button> emptyPositions) {
        return new Random().nextInt(emptyPositions.size());
    }

    private List<Button> filterForEmptyPositions() {
        return Arrays.stream(buttons)
                .filter(button -> button.getText().equals(" "))
                .collect(Collectors.toList());
    }

}
