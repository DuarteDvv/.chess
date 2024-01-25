module game_components {
    requires javafx.controls;
    requires javafx.fxml;

    opens game_components to javafx.fxml;
    exports game_components;
}
