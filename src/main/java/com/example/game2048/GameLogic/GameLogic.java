package com.example.game2048.GameLogic;

import com.example.game2048.GameUI.GameWindow;
import com.example.game2048.GameUI.Grid;
import com.example.game2048.GameUI.Tile;
import com.example.game2048.util.Constants;

import java.util.Arrays;


import static com.example.game2048.util.Constants.BOARD_SIZE;


public class GameLogic {

    private GameWindow gameWindow;

    private  Board board;

    private GameOverCheck gameCondition;

    private int score;

    public GameLogic(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.board = new Board();
        this.gameCondition = new GameOverCheck();
        this.score = 0;
    }

    public void initialize() {
        updateGrid();
        gameWindow.setScore(0);
        board.printBoard();
    }

    public void moveTiles(MoveDirection direction) {

        int[][] copyboard = Arrays.stream(board.getBoard()).map(int[]::clone).toArray(int[][]::new);

        switch (direction) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }

        // Spawn a new tile after each move only if the rows are modified
        if(!Arrays.deepEquals(copyboard, board.getBoard())) {
            //grid.spawnNumber();
            board.spawnTile();
            updateGrid();
            board.printBoard();// for debugging purposes
            gameCondition.checkGameOverOrWin();
        }
        gameWindow.setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void updateGrid() {
        Grid grid = gameWindow.getGrid();
        grid.updateTiles(board.getBoard());
    }

    // Merge the row by sliding and combining identical tiles
    // This method is used by all movement methods
    private int[] mergeRow(int[] row) {
        int[] newRow = new int[Constants.BOARD_SIZE];
        int idx = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (row[i] != 0) {
                if (idx > 0 && newRow[idx - 1] == row[i]) {
                    newRow[idx - 1] *= 2; // Merge with previous tile
                    score += newRow[idx - 1];
                } else {
                    newRow[idx++] = row[i];  // Slide the tile
                }
            }
        }
        return newRow;
    }

    // Other movement methods (right, up, down) call similar helper functions
    private int[] reverseArray(int[] row) {
        int[] reversed = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            reversed[i] = row[row.length - 1 - i];
        }
        return reversed;
    }

    // Method used for vertical sliding of tiles
    private void transposeMatrix(){
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = row + 1; col < BOARD_SIZE; col++) {
                int temp = board.getTileValue(row, col);
                board.setTileValue(row, col, board.getTileValue(col, row));
                board.setTileValue(col, row, temp);
            }
        }
    }

    // Methods used for movement of tiles (sliding and combining)
    public void moveLeft() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            int[] newRow = mergeRow(board.getRow(row));
            board.setRow(row, newRow);
        }
    }

    public void moveRight() {
        // Reverse rows, apply mergeRow(), then reverse back
        for (int row = 0; row < BOARD_SIZE; row++) {
            // Reverse the row
            int[] reversedRow = reverseArray(board.getRow(row));
            // Merge the reversed row (same as the logic for moving left)
            int[] mergedRow = mergeRow(reversedRow);
            mergedRow = reverseArray(mergedRow);
            // Reverse the row back and update the board
            board.setRow(row, mergedRow);
        }
    }

    public void moveUp() {
        // Transpose the matrix, apply moveLeft(), then transpose back
        transposeMatrix();
        moveLeft();
        transposeMatrix();
    }

    public void moveDown() {

        transposeMatrix();
        moveRight();
        transposeMatrix();
    }

    public Board getCurrentState(){
        return board;
    }
}
