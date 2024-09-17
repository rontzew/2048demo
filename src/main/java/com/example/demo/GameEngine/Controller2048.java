package com.example.demo.GameEngine;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller2048 {
    @FXML
    private Label game;

    @FXML
    protected void gameInit() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);  // Optional, shows grid lines for clarity

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Label tile = new Label("0");
                tile.setMinSize(100, 100);
                tile.setStyle("-fx-background-color: lightgray; -fx-font-size: 24px;");
                grid.add(tile, col, row);  // Add tile at the correct position
            }
        }
    }

}