package game_components;

import java.io.IOException;
import javafx.fxml.FXML;

public class GameScreen {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("startScreen");
    }
}