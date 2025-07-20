package com.example.game2048.GameUI;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Tile {

    private int value;          // The tile's value (e.g., 2, 4, 8)
    private StackPane node;     // The visual node for the tile
    private Label label;        // Label to display the tile's value

    public Tile(){
        this(0);
    }

    public Tile(int value) {
        this.value = value;
        this.label = new Label(String.valueOf(value));
        this.label.setMinSize(100, 100);
        this.label.setStyle(setStyleByNumber(value));
        node = new StackPane(label);  // Add label to node
    }

    public Node getNode() {
        return node;
    }

    public int getValue(){
        return this.value;
    }

    //  Method to set a new value to the tile

    public void setValue(int newValue) {
        this.value = newValue;  // Update the value
        label.setText(this.value == 0 ? "" : String.valueOf(this.value));  // Update the label's text
    }

    private String setStyleByNumber(int val){
        switch (val) {
            case 0: label.setText(this.value == 0 ? "" : String.valueOf(this.value));
            return "-fx-border-color: gray; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: lightgray;";
            case 2: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #D996A1;";
            case 4: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #D48795;";
            case 8: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #CE7888;";
            case 16: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #C96979;";
            case 32: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #C45A6D;";
            case 64: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #BE4B60;";
            case 128: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #B44156;";
            case 256: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #A53B4F;";
            case 512: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #963648;";
            case 1024: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #873140;";
            case 2048: return "-fx-border-color: black; -fx-font-size: 24; -fx-alignment: center; -fx-background-color: #782B38;";
            default: return "-fx-background-color: #cdc1b4; -fx-font-size: 24px; -fx-text-fill: #776e65; -fx-alignment: center;";
        }
    }

    //  animations for the tiles
    private static void animateTileMovement(Label tile, double fromX, double fromY, double toX, double toY) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(tile);
        translate.setDuration(Duration.millis(200));  // Set the animation duration
        translate.setFromX(fromX);
        translate.setFromY(fromY);
        translate.setToX(toX);
        translate.setToY(toY);
        translate.play();
    }

    public static void animateTileMerge(Label tile) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(150), tile);
        scale.setToX(1.2);  // Scale 20% larger
        scale.setToY(1.2);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);  // Return to normal size
        scale.play();
    }

    public static void animateNewTile(Label tile) {
        FadeTransition fade = new FadeTransition(Duration.millis(300), tile);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public Label getLabel() {
        return this.label;
    }
}
