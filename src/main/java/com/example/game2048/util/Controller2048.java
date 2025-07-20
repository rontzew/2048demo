package com.example.game2048.util;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller2048 {
    @FXML
    private GridPane grid;

    public GridPane getGrid() {
        return grid;
    }

    @FXML
    protected void gameInit() {
        grid = getGrid();
    }
}