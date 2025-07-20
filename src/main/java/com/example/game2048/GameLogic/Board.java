package com.example.game2048.GameLogic;

import com.example.game2048.GameUI.Grid;
import com.example.game2048.GameUI.Tile;
import com.example.game2048.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int[][] board;


    public Board() {
        this.board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        spawnTile();
        spawnTile();
        // Initialize the board with empty spaces and new tiles
    }

    public int spawnTile() {
        List<int[]> emptySpaces = new ArrayList<>(); //creates a list with all empty tiles
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                if (board[row][col] == 0) {          //an empty tile has a value of 0, but it is hidden
                    emptySpaces.add(new int[]{row, col});
                }
            }
        }
        //If there are empty spaces, one will be chosen randomly and a tile will be spawned there
        int newValue = 0;
        if (!emptySpaces.isEmpty()) {
            int[] randomSpace = emptySpaces.get(new Random().nextInt(emptySpaces.size()));
            newValue = new Random().nextDouble() < 0.9 ? 2 : 4;
            board[randomSpace[0]][randomSpace[1]] = newValue; //update the internal board state
        }
        return newValue;
    }

    // Getter for the tile value at a specific position
    public int getTileValue(int row, int col) {
        return board[row][col];
    }

    // Returns the entire board as a 2D array
    public int[][] getBoard(){
        return board;
    }

    // Get a row of the board as an array
    public int[] getRow(int row){
        return board[row];
    }

    // Set a row in the board with a new array
    public void setRow(int row, int[] newRow){
        this.board[row] = newRow;
    }

    // Set the value of a specific tile
    public void setTileValue(int row, int col, int value) {
        this.board[row][col] = value;
    }

    public void printBoard(){
        for (int i = 0; i<Constants.BOARD_SIZE; i++){
            for (int j = 0; j<Constants.BOARD_SIZE; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
