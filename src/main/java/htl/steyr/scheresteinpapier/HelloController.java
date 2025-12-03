package htl.steyr.scheresteinpapier;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Random;

public class HelloController {
    public ProgressBar pBar;
    public ImageView playerBox1; //Spieler-Bild
    public ImageView playerBox2; //Computer-Bild
    public Label chooseWinner;  // label that shows winner


    private final Random rand = new Random();
    public TextField spielerHighscore;
    public TextField computerHighscore;
    private String computer;
    public String playerChoice;
    private MediaPlayer backgroundMusic;  //Hintergrundmusik

    public void onScissorsClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/schere.png"))));
        playerChoice = "schere.png";
        mainProcess();
    }

    public void onRockClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/stein.png"))));
        playerChoice = "stein.png";
        mainProcess();
    }

    public void onPaperClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/papier.png"))));
        playerChoice = "papier.png";
        mainProcess();
    }

    public void mainProcess() {
        String[] wahl = {"schere.png", "stein.png", "papier.png"};
        computer = wahl[rand.nextInt(3)];

        pBar.setProgress(0);

        PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
        pause1.setOnFinished(e -> {
            pBar.setProgress(0.34);

            PauseTransition pause2 = new PauseTransition(Duration.seconds(0.5));
            pause2.setOnFinished(ev -> {
                pBar.setProgress(1.0);
                playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computer))));
                chooseAWinner();
            });
            pause2.play();
        });
        pause1.play();
    }

    public void resetButtonClicked(ActionEvent actionEvent) {
        playerBox1.setImage(null);
        playerBox2.setImage(null);
        spielerHighscore.setText("0");
        computerHighscore.setText("0");
        chooseWinner.setText("Willkommen!!");
        chooseWinner.setStyle("-fx-text-fill: black;");
        chooseWinner.setEffect(null);
        pBar.setProgress(0);
    }

    public void chooseAWinner() {
        if (playerChoice.equals("schere.png") && computer.equals("papier.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("stein.png") && computer.equals("schere.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("papier.png") && computer.equals("stein.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals(computer)) {
            chooseWinner.setText("Unentschieden!");
            drawStyle();
        } else {
            chooseWinner.setText("Du verlierst!");
            loseStyle();
        }
    }

    private void winStyle() {
        chooseWinner.setStyle("-fx-text-fill: limegreen;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));
    }

    private void loseStyle() {
        chooseWinner.setStyle("-fx-text-fill: red;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));
    }

    private void drawStyle() {
        chooseWinner.setStyle("-fx-text-fill: gray;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.3));
    }



    public void initialize() {
        try {
            Media pick = new Media(getClass().getResource("/sounds/lease.mp3").toExternalForm());
            backgroundMusic = new MediaPlayer(pick);

            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE); // speilt die mp3 endlos
            backgroundMusic.play();

        } catch (Exception e) {
            System.out.println("Musik konnte nicht geladen werden: " + e.getMessage());
        }
    }
}
