package com.example.game2048;


import com.example.game2048.GameLogic.GameLogic;
import com.example.game2048.GameUI.GameWindow;
import com.example.game2048.GameUI.Grid;
import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameWindow gameWindow = new GameWindow(primaryStage);
        GameLogic gameLogic = new GameLogic(gameWindow);
        gameWindow.setGameLogic(gameLogic);
        gameWindow.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
