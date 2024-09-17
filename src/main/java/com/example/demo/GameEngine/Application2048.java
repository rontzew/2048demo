package com.example.demo.GameEngine;

import com.example.demo.GameLogic.GameLogic;
import com.example.demo.GameLogic.GameOverCheck;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Application2048 extends Application {
    private GameLogic game;
    private GameOverCheck check = new GameOverCheck();
    private GridPane grid;


    @Override
    public void start(Stage stage) {

        game = new GameLogic();
        grid = new GridPane();

        updateBoard();

        Scene scene = new Scene(grid, 400, 400);
        stage.setScene(scene);
        stage.setTitle("2048 Game");
        stage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    game.moveUp();
                    break;
                case DOWN:
                    game.moveDown();
                    break;
                case LEFT:
                    game.moveLeft();
                    break;
                case RIGHT:
                    game.moveRight();
                    break;
            }
            updateBoard();
            checkGameOverOrWin();
        });
    }

    private String getStyleByNumber(int val){
        switch (val) {
            case 2: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #D996A1;";
            case 4: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #D48795;";
            case 8: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #CE7888;";
            case 16: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #C96979;";
            case 32: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #C45A6D;";
            case 64: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #BE4B60;";
            case 128: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #B44156;";
            case 256: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #A53B4F;";
            case 512: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #963648;";
            case 1024: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #873140;";
            case 2048: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #782B38;";
            default: return "-fx-background-color: #cdc1b4; -fx-font-size: 24px; -fx-text-fill: #776e65; -fx-alignment: center;";
        }
    }

    private void updateBoard() {
        grid.getChildren().clear();  // Clear the old grid

        int[][] board = game.getBoard();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Label tile = new Label(board[row][col] == 0 ? "" : String.valueOf(board[row][col]));
                tile.setMinSize(100, 100);
                tile.setStyle(getStyleByNumber(board[row][col]));
                tile.setAlignment(Pos.CENTER);
                grid.add(tile, col, row);
            }
        }
    }

    private void checkGameOverOrWin() {
        if (check.isGameWon()) {
            System.out.println("You win!");
        } else if (check.isGameOver()) {
            System.out.println("Game over!");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
