package com.example.demo.GameEngine;

import com.example.demo.GameLogic.GameLogic;
import com.example.demo.GameLogic.GameOverCheck;
import com.example.demo.Animations.Animations;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Application2048 extends Application {
    private GameLogic game;
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
                Label newTile = new Label(board[row][col] == 0 ? "" : String.valueOf(board[row][col]));
                newTile.setMinSize(100, 100);
                newTile.setStyle(getStyleByNumber(board[row][col]));
                newTile.setAlignment(Pos.CENTER);
                grid.add(newTile, col, row);

                if (board[row][col] == 0) {
                    Animations.animateNewTile(newTile);    // New tile fade-in
                }


                // else if (/* logic to check if the tile was merged */) {
                    // Tile merge animation
                    //Animations.animateTileMerge(tile);
                //} //else if (/* logic to check if the tile was moved */) {
                    // Tile move animation
                    //Animations.animateTileMovement(tile, /* fromX */, /* fromY */, /* toX */, /* toY */);

            }


        }
    }

    private void checkGameOverOrWin() {
        if (GameOverCheck.isGameWon()) {
            System.out.println("You win!");
        } else if (GameOverCheck.isGameOver()) {
            gameOver();
        }
    }

    private void gameOver() {
        // Show a dialog or message indicating the game is over
        System.out.println("Game Over! No moves possible.");

        //display a message using a JavaFX alert box
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("No more moves possible!");
        alert.showAndWait();

        //TBD: logic to stop the game or reset the board
    }

    public static void main(String[] args) {
        launch();
    }
}
