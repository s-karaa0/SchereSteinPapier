package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class HelloController {
    public ProgressBar pBar;
    public ImageView playerBox1; //Spieler Bild
    public ImageView playerBox2; //Computer Bild
    public Label chooseWinner;  // label that shows winner
    public String playerChoice;


    private final Random rand = new Random();
    private String computer;

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
        playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computer))));
        chooseAWinner();
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
    private void winStyle() { // einfacher glow effekt wenn man gewinnt
        chooseWinner.setStyle("-fx-text-fill: limegreen;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));
    }

    private void loseStyle() {// einfacher glow effekt wenn man verliert
        chooseWinner.setStyle("-fx-text-fill: red;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));
    }

    private void drawStyle() {// einfacher glow effekt wenn es ein unentschieden ist
        chooseWinner.setStyle("-fx-text-fill: gray;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.3));
    }
}
