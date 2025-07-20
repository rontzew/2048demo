package com.example.game2048.GameUI;

import com.example.game2048.GameLogic.GameLogic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.example.game2048.GameLogic.MoveDirection.*;

public class GameWindow {

    private GameLogic gameLogic;
    private Grid grid;
    private Stage primaryStage;
    private Label scoreLabel;

    public GameWindow(Stage stage) {
        this.primaryStage = stage;
        setupUI();
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void setupUI() {
        grid = new Grid();
        grid.initializeGrid();

        BorderPane root = new BorderPane();
        root.setCenter(grid.getGridPane());
        scoreLabel = new Label("Score: 0");
        scoreLabel.setMinSize(100, 50);
        scoreLabel.setStyle("-fx-font-size: 24; -fx-alignment: center; -fx-background-color: lightgray;");
        scoreLabel.setAlignment(Pos.CENTER);
        root.setTop(scoreLabel);

        Scene scene = new Scene(root, 450, 480);
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            if (gameLogic != null) {
                switch (event.getCode()) {
                    case UP:
                        gameLogic.moveTiles(UP);
                        break;
                    case DOWN:
                        gameLogic.moveTiles(DOWN);
                        break;
                    case LEFT:
                        gameLogic.moveTiles(LEFT);
                        break;
                    case RIGHT:
                        gameLogic.moveTiles(RIGHT);
                        break;
                    default:
                        break;
                }
                updateBoard();
            }
        });

    }

    public void updateBoard() {
        if (gameLogic != null) {
            grid.clear();
            gameLogic.updateGrid();
            System.out.println();
        }
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("2048 Game");
        primaryStage.show();
        if (gameLogic != null) {
            gameLogic.initialize();
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}