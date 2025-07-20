package com.example.game2048.GameLogic;

import com.example.game2048.util.Constants;
import javafx.scene.control.Alert;

public class GameOverCheck {

    static Board board = new Board();
    static int[][] copyBoard = board.getBoard();


    // Check if the game is over (no valid moves)
    public static boolean isGameOver() {
        // Check for any available moves or empty spaces
        return checkBoardFull() && !areMovesPossible();
    }

    private static boolean checkBoardFull(){
        boolean boardIsFull = true;

        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
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
    private static boolean areMovesPossible() {
        // Check in 4 orthogonal directions (up, down, left, right)
        final int[] dx = {-1, 1, 0, 0};  // Up, Down
        final int[] dy = {0, 0, -1, 1};  // Left, Right

        int rows = copyBoard.length;
        int cols = copyBoard[0].length;

        // Traverse each element in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check in all 4 orthogonal directions (up, down, left, right)
                for (int dir = 0; dir < 4; dir++) {
                    int newX = i + dx[dir];
                    int newY = j + dy[dir];

                    // Ensure the neighbor is within bounds
                    if (isValid(newX, newY, rows, cols)) {
                        // Check if the current tile can merge with a neighbor
                        if (copyBoard[i][j] == copyBoard[newX][newY] || copyBoard[newX][newY] == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;  // No possible moves left
    }



    // Check if the player has won (2048 tile)
    public static boolean isGameWon() {
        for (int[] row : copyBoard) {
            for (int tile : row) {
                if (tile == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void checkGameOverOrWin() {
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

}
