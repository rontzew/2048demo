package com.example.game2048.GameUI;

import com.example.game2048.util.Constants;
import com.example.game2048.GameLogic.Board;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Grid {
    private GridPane gridPane;
    private Tile[][] tiles;

    private Board board;

    public Grid() {
        this.gridPane = new GridPane();
        this.tiles = new Tile[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        initializeGrid();
    }

    public void initializeGrid() {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                Tile tile = new Tile(0);
                addTile(tile, col, row);  // Add tile at the correct position
            }
        }
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);  // Optional, shows grid lines for clarity
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void addTile(Tile tile, int row, int col) {
        tiles[row][col] = tile;
        gridPane.add(tile.getNode(), col, row);
    }

    public void removeTile(int row, int col) {
        // Remove any node from the specified position
        Node node = getNodeFromGridPane(row, col);
        if (node != null) {
            gridPane.getChildren().remove(node);
        }
    }

    public Node getNodeFromGridPane(int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }

    public void spawnNumber(){
        Tile newTile = new Tile();

    }

    public void updateTiles(int[][] board) {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                    Tile tile = new Tile(board[row][col]);
                    tile.setValue(board[row][col]);
                    // Update the tile's value
                    addTile(tile, row, col);
            }
        }
    }

    public void clear(){
        gridPane.getChildren().clear();
    }
}
