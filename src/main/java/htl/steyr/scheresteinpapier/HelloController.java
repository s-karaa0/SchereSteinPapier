package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class HelloController {
    public ProgressBar pBar;
    public TextArea playerBox2;
    public TextArea playerBox1;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onScissorsClicked(ActionEvent actionEvent) {
    }

    public void onRockClicked(ActionEvent actionEvent) {
    }

    public void onPaperClicked(ActionEvent actionEvent) {
    }
}
