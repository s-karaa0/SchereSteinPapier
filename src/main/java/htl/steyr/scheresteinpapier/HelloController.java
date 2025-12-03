package htl.steyr.scheresteinpapier;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    /**
     * Wenn der/die Spieler/in auf den Schere-Button drückt, soll der passender
     * Image aufgeladen werden. Danach wird mainProcess() aufgerufen, wo die
     * Hauptfunktion stattfindet.
     */
    public void onScissorsClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/schere.png"))));
        playerChoice = "schere.png"; // saves players choice
        mainProcess();
    }

    /**
     * In dieser Methode soll ein Bild von einem Stein aufgeladen werden. Diese
     * hat dieselbe Funktion wie die Methode drüber, nur mit einem anderen URL.
     */
    public void onRockClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/stein.png"))));
        playerChoice = "stein.png"; // saves players choice
        mainProcess();

    }

    /**
     * In dieser Methode soll ein Bild von einem Papier aufgeladen werden.
     */
    public void onPaperClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/papier.png"))));
        playerChoice = "papier.png"; // saves players choice
        mainProcess();
    }

    /**
     * In dieser Methode ist die Hauptfunktion implementiert worden. Sie ist
     * passend zu alle anderen Methoden, die diese aufrufen.
     */
    public void mainProcess() {
        String[] wahl = {"schere.png", "stein.png", "papier.png"}; // Optionen
        computer = wahl[rand.nextInt(3)]; // zufällige Wahl des Computers

        pBar.setProgress(0); // resette die bar jedes mal wenn main process aufgerufen wird

        PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
        pause1.setOnFinished(e -> {// (e -> { ... }) ist ein Lambda: Kurzform für eine Methode, die auf das Ereignis e reagiert

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


    /**
     * Bringt alles auf den Anfangszustand - das Spiel kann neu beginnen.
     */
    public void resetButtonClicked(ActionEvent actionEvent) {
        playerBox1.setImage(null);
        playerBox2.setImage(null);
        spielerHighscore.setText("0");
        computerHighscore.setText("0");
        chooseWinner.setText("Willkommen!!");
        chooseWinner.setStyle("-fx-text-fill: black;"); // Farbe wird auf schwarz zurückgesetzt
        chooseWinner.setEffect(null); // entfernt den Glow effekt
        pBar.setProgress(0); // resette die bar jedes mal wenn main process aufgerufen wird
    }

  // Vergleicht Wahl des Spielers und Computers und entscheidet bassierend darauf wer gewonnen hat
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
