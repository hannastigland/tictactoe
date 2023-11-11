package se.iths.tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HelloController {
    private final Model model = new Model();
    public Label humanPlayer, aiPlayer, whoWinsLabel;
    public Button resetButton, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    public Button[] buttons;
    private boolean gameOver;

    public void initialize() {
        buttons = new Button[] {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        Arrays.asList(buttons).forEach(b -> b.setFocusTraversable(false));
        startGame();
    }

    private void startGame() {
        gameOver = false;
        model.currentPlayer = model.player1.getPlayer();
        setXo();
    }

    public void resetButtonClicked() {
        model.initializeGameBoard();
        gameOver = false;
        startGame();
        whoWinsLabel.setText("Winner: ");
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

    private void setXo() {
        for (Button button : buttons) {
            Integer rowIndex = GridPane.getRowIndex(button);
            Integer columnIndex = GridPane.getColumnIndex(button);
            button.setText(model.getSymbolAt(rowIndex, columnIndex));
        }
    }
    public void handlePlayerTurn(MouseEvent mouseEvent) {
        if ("X".equals(model.currentPlayer)&& !gameOver) {
            handlePlayerMove(mouseEvent);
        }
    }

    private void handlePlayerMove(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        if (source instanceof Button clickedButton) {
            handleButtonClick(clickedButton);
            handleAiTurn();
        }
    }

    public void handleButtonClick(Button button) {
        Integer rowIndex = GridPane.getRowIndex(button);
        Integer columnIndex = GridPane.getColumnIndex(button);

        if (model.isMoveValid(rowIndex, columnIndex)) {
            button.setText(model.currentPlayer);
            updateModel(rowIndex, columnIndex);

            if (model.checkWinnerOrDraw()) {
                updateScoreLabels();
                gameOver = true;
                if (model.isDraw()) {
                    whoWinsLabel.setText("Winner: Draw!");
                } else {
                    whoWinsLabel.setText("Winner: " + model.currentPlayer);
                }
                disableAllButtons();
                } else if (model.isBoardFull()) {
                    gameOver = true;
                    whoWinsLabel.setText("Winner: Draw!");
                    disableAllButtons();
                } else {
                    model.switchCurrentPlayer();
                    handleAiTurn();
                }
            button.setDisable(true);
        }
    }

    private void disableAllButtons() {
        for (Button b : buttons) {
            b.setDisable(true);
        }
    }

    private void updateModel(int rowIndex, int columnIndex) {
        model.placeSymbolAt(rowIndex, columnIndex, model.currentPlayer);
    }

    private void updateScoreLabels() {
        int playerScore = model.player1.getScore();
        int aiScore = model.aiPlayer.getScore();
        humanPlayer.setText("Your score: " + (playerScore));
        aiPlayer.setText("Computer score: " + (aiScore));
    }

    public void handleAiTurn() {
        if ("O".equals(model.currentPlayer) && !gameOver) {
            List<Button> emptyPositions = getEmptyButtons();
            if (!emptyPositions.isEmpty()) {
                int selected = getRandomPosition(emptyPositions);
                Button button = emptyPositions.get(selected);
                handleButtonClick(button);
            }
        }
    }

    private static int getRandomPosition(List<Button> emptyPositions) {
        return new Random().nextInt(emptyPositions.size());
    }

    private List<Button> getEmptyButtons() {
        return Arrays.stream(buttons)
                .filter(button -> button.getText().equals(" "))
                .collect(Collectors.toList());
    }

}
