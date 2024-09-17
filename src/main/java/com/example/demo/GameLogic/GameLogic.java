package com.example.demo.GameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameLogic {

    protected final int[][] board;
    protected static final int SIZE = 4;


    public GameLogic() {
        board = new int[SIZE][SIZE];
        spawnTile();
        spawnTile();  // Spawn two tiles initially
    }

    // Merge the row by sliding and combining identical tiles
    private int[] mergeRow(int[] row) {
        int[] newRow = new int[SIZE];
        int idx = 0;

        for (int i = 0; i < SIZE; i++) {
            if (row[i] != 0) {
                if (idx > 0 && newRow[idx - 1] == row[i]) {
                    newRow[idx - 1] *= 2; // Merge with previous tile
                } else {
                    newRow[idx++] = row[i];  // Slide the tile
                }
            }
        }
        return newRow;
    }

    // Spawns a new tile (either 2 or 4) at a random empty spot
    private void spawnTile() {
        List<int[]> emptySpaces = new ArrayList<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    emptySpaces.add(new int[]{row, col});
                }
            }
        }

        if (!emptySpaces.isEmpty()) {
            int[] randomSpace = emptySpaces.get(new Random().nextInt(emptySpaces.size()));
            board[randomSpace[0]][randomSpace[1]] = new Random().nextDouble() < 0.9 ? 2 : 4;
        }
    }

    // Other movement methods (right, up, down) would call similar helper functions
    private int[] reverseArray(int[] row) {
        int[] reversed = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            reversed[i] = row[row.length - 1 - i];
        }
        return reversed;
    }

    // Method used for vertical sliding of tiles
    private void transposeMatrix(){
        for (int row = 0; row < SIZE; row++) {
            for (int col = row + 1; col < SIZE; col++) {
                int temp = board[row][col];
                board[row][col] = board[col][row];
                board[col][row] = temp;
            }
        }
    }


    // Method to move and merge tiles to the left
    public void moveLeft() {
        int[][] copyboard = board.clone();
        for (int row = 0; row < SIZE; row++) {
            int[] newRow = mergeRow(board[row]);
            board[row] = newRow;
        }
        // Spawn a new tile after each move only if the rows are modified
        if(!Arrays.deepEquals(copyboard, board)) {
            spawnTile();
        }
    }

    public void moveRight() {
        int[][] copyboard = board.clone();
        // Reverse rows, apply mergeRow(), then reverse back
        for (int row = 0; row < SIZE; row++) {
            // Reverse the row
            int[] reversedRow = reverseArray(board[row]);

            // Merge the reversed row (same as the logic for moving left)
            int[] mergedRow = mergeRow(reversedRow);

            // Reverse the row back and update the board
            board[row] = reverseArray(mergedRow);
        }
        // Spawn a new tile after making a move if tiles merged or at least slid
        if(!Arrays.deepEquals(copyboard, board)) {
            spawnTile();
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

    public int[][] getBoard() {
        return board;
    }
}
