module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.GameLogic;
    exports com.example.demo.GameEngine;
    opens com.example.demo.GameEngine to javafx.fxml;
    opens com.example.demo.GameLogic to javafx.fxml;
}