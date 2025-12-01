package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class HelloController {
    public ProgressBar pBar;
    public ImageView playerBox1; //Spieler Bild
    public ImageView playerBox2; //Computer Bild

    private final Random rand = new Random();
    public TextField spielerHighscore;
    public TextField computerHighscore;
    private String computer;

    public void onScissorsClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/schere.png"))));
        mainProcess();
    }

    public void onRockClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/stein.png"))));
        mainProcess();
    }

    public void onPaperClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/papier.png"))));
        mainProcess();
    }

    public void mainProcess() {
        String[] wahl = {"schere.png", "stein.png", "papier.png"};
        computer = wahl[rand.nextInt(3)];
        playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computer))));
    }

    public void resetButtonClicked(ActionEvent actionEvent) {
        playerBox1.setImage(null);
        playerBox2.setImage(null);
        spielerHighscore.setText("0");
        computerHighscore.setText("0");
    }
}
