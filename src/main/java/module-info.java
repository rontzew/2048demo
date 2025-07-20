module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    // Export the base package to both javafx.fxml and javafx.graphics
    exports com.example.game2048 to javafx.fxml, javafx.graphics;

    // Export other subpackages
    exports com.example.game2048.GameLogic;
    exports com.example.game2048.util;

    // Allow reflective access to specific packages for FXML loading
    opens com.example.game2048.util to javafx.fxml;
    opens com.example.game2048.GameLogic to javafx.fxml;
}