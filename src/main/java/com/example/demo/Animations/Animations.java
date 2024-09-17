package com.example.demo.Animations;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Animations {

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
}
