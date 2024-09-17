package com.example.demo.GameLogic;

public class GameOverCheck {

    GameLogic logic = new GameLogic();
    int[][] copyBoard = logic.getBoard();


    // Check if the game is over (no valid moves)
    public boolean isGameOver() {
        // Check for any available moves or empty spaces

        return checkBoardFull() && areMovesPossible();
    }

    private boolean checkBoardFull(){
        boolean boardIsFull = true;

        for (int row = 0; row < GameLogic.SIZE; row++) {
            for (int col = 0; col < GameLogic.SIZE; col++) {
                if (copyBoard[row][col] == 0) {
                    boardIsFull = false;
                    break;
                }
            }
        }
        return boardIsFull;
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    private boolean areMovesPossible(){
        // Search in orthogonal directions if there are tiles with same value

        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};

        int rows = copyBoard.length;
        int cols = copyBoard[0].length;

        // Traverse each element in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check all 8 directions for each element
                for (int dir = 0; dir < 4; dir++) {
                    int newX = i + dx[dir];
                    int newY = j + dy[dir];

                    // Ensure the neighbor is within bounds
                    if (isValid(newX, newY, rows, cols)) {
                        // Compare current element with neighbor
                        if (copyBoard[i][j] == copyBoard[newX][newY]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Check if the player has won (2048 tile)
    public boolean isGameWon() {
        for (int[] row : copyBoard) {
            for (int tile : row) {
                if (tile == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

}
