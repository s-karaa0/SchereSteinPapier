package htl.steyr.scheresteinpapier;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    public TextField spielerHighscoreFeld; //TextField für Spieler-Highscore
    public TextField computerHighscoreFeld; //TextField für Computer-Highscore
    public AnchorPane anchorPaneForSeries;
    public Button yesButton;
    public Button noButton;
    public Label seriesText;
    public TextField spielerSerieFeld;
    public TextField computerSerieFeld;
    private String computerChoice;
    public String playerChoice;
    private boolean answer;
    private MediaPlayer backgroundMusic;  //Hintergrundmusik
    public boolean isLeaseMusic = true; //anfangslied heisst lease deshalb der name

    private int highscoreSpieler = 0; //Anzahl der Gewinne von Spieler
    private int highscoreComputer = 0; //Anzahl der Gewinne von Computer

    private int serieSpieler = 0; //Anzahl der Serien von Spieler
    private int serieComputer = 0; //Anzahl der Serien von Computer

    /**
     * Wenn der/die Spieler/in auf den Schere-Button drückt, soll der passender
     * Image aufgeladen werden. Danach wird mainProcess() aufgerufen, wo die
     * Hauptfunktion stattfindet.
     */
    public void onScissorsClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/schere.png"))));
        playerChoice = "schere.png"; //speichert Spielers Auswahl
        mainProcess();
    }

    /**
     * In dieser Methode soll ein Bild von einem Stein aufgeladen werden. Diese
     * hat dieselbe Funktion wie die Methode drüber, nur mit einem anderen URL.
     */
    public void onRockClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/stein.png"))));
        playerChoice = "stein.png"; //speichert Spielers Auswahl
        mainProcess();
    }

    /**
     * In dieser Methode soll ein Bild von einem Papier aufgeladen werden.
     */
    public void onPaperClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/papier.png"))));
        playerChoice = "papier.png"; //speichert Spielers Auswahl
        mainProcess();
    }

    /**
     * In dieser Methode ist die Hauptfunktion implementiert worden. Sie ist
     * passend zu alle anderen Methoden, die diese aufrufen.
     */
    public void mainProcess() {
        String[] wahl = {"schere.png", "stein.png", "papier.png"}; // Optionen
        computerChoice = wahl[rand.nextInt(3)]; // zufällige Wahl des Computers

        pBar.setProgress(0); //resette die bar jedes mal wenn main process aufgerufen wird
        pBar.setProgress(0);

        PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
        pause1.setOnFinished(e -> {
            pBar.setProgress(0.34);

            PauseTransition pause2 = new PauseTransition(Duration.seconds(0.5));
            pause2.setOnFinished(ev -> {
                pBar.setProgress(1.0);

                playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computerChoice))));
                playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computerChoice))));
                chooseAWinner();
                spielerHighscoreFeld.setText(String.valueOf(highscoreSpieler));
                computerHighscoreFeld.setText(String.valueOf(highscoreComputer));

                if (highscoreSpieler >= 2 || highscoreComputer >= 2) {
                    if (answer == false) {
                        serie();
                    }
                    spielerSerieFeld.setText(String.valueOf(serieSpieler));
                    computerSerieFeld.setText(String.valueOf(serieComputer));
                }
            });
            pause2.play();
        });
        pause1.play();


    }


    /**
     * Bringt alles auf den Anfangszustand -> das Spiel kann neu beginnen.
     */
    public void resetButtonClicked() {
        playerBox1.setImage(null);
        playerBox2.setImage(null);
        spielerHighscoreFeld.setText("0");
        computerHighscoreFeld.setText("0");
        chooseWinner.setText("Willkommen!!");
        chooseWinner.setStyle("-fx-text-fill: black;"); //Farbe wird auf schwarz zurückgesetzt
        chooseWinner.setEffect(null); //entfernt den Glow effekt
        pBar.setProgress(0); //resette die bar jedes mal wenn main process aufgerufen wird
        highscoreComputer = 0;
        highscoreSpieler = 0;
    }

    /**
     * Diese Methode vergleicht die Wahl des Spielers und Computers und entscheidet
     * bassierend darauf wer gewonnen hat.
     */
    public void chooseAWinner() {

        if (playerChoice.equals("schere.png") && computerChoice.equals("papier.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("stein.png") && computerChoice.equals("schere.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("papier.png") && computerChoice.equals("stein.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("brunnen.png") && computerChoice.equals("stein.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals("brunnen.png") && computerChoice.equals("schere.png")) {
            chooseWinner.setText("Du gewinnst!");
            winStyle();
        } else if (playerChoice.equals(computerChoice)) {
            chooseWinner.setText("Unentschieden!");
            drawStyle();
        } else {
            chooseWinner.setText("Du verlierst!");
            loseStyle();
        }
    }


    private void winStyle() { //einfacher glow effekt wenn man gewinnt
        chooseWinner.setStyle("-fx-text-fill: limegreen;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));

        highscoreSpieler += 1;
    }

    private void loseStyle() { //einfacher glow effekt wenn man verliert
        chooseWinner.setStyle("-fx-text-fill: red;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.6));

        highscoreComputer += 1;
    }

    private void drawStyle() { //einfacher glow effekt wenn es ein unentschieden ist
        chooseWinner.setStyle("-fx-text-fill: gray;");
        chooseWinner.setEffect(new javafx.scene.effect.Glow(0.3));
        String[] wahl = {"schere.png", "stein.png", "papier.png", "brunnen.png"};
        computerChoice = wahl[rand.nextInt(4)];
        playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computerChoice))));
    }

    /**
     * Diese Funktion soll ähnlich wie die oberen Funktion ein Bild eines Brunnen darstellen
     *
     *
     */
    public void onWellClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/brunnen.png"))));
        playerChoice="brunnen.png";
        mainProcess();
    }

    private void serie() {
        anchorPaneForSeries.setVisible(true);

        if (highscoreSpieler > highscoreComputer) {
            seriesText.setText("Du hast 10mal gewonnen\n-> Eine Serie geschafft.\nWillst du neu anfangen?");
            serieSpieler += 1;
        } else {
            seriesText.setText("Der Computer hat 10mal gewonnen\n-> Eine Serie geschafft.\nWillst du neu anfangen?");
            serieComputer += 1;
        }

        if (yesButton.isPressed()) {
            yesButtonForSeries();
        } else if (noButton.isPressed()) {
            noButtonForSeries();
        }
    }

    public void yesButtonForSeries() {
        resetButtonClicked();
        answer = false;
        anchorPaneForSeries.setVisible(false);
    }

    public void noButtonForSeries() {
        answer = true;
        anchorPaneForSeries.setVisible(false);
    }

    public void initialize() {
        try {
            Media pick = new Media(getClass().getResource("/sounds/lease.mp3").toExternalForm());
            backgroundMusic = new MediaPlayer(pick);

            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE); // spielt die mp3 endlos
            backgroundMusic.play();

        } catch (Exception e) {
            System.out.println("Musik konnte nicht geladen werden: " + e.getMessage());
        }
    }

    public void ChangeMusicClicked(ActionEvent actionEvent) {
        String nextSong;
        try {

            backgroundMusic.stop();

            // Hier wird entschieden welcher song gespielt werden soll
            if (isLeaseMusic) {
                nextSong = "/sounds/Ocean.mp3";
            } else {
                nextSong = "/sounds/lease.mp3";
            }

            // Media neu laden um den neuen song zu spielen
            Media pick = new Media(getClass().getResource(nextSong).toExternalForm());
            backgroundMusic = new MediaPlayer(pick);

            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();

            // Wechselt den Zustand damit beim nächsten drücken des knopfes der andere Track gewählt wird
            isLeaseMusic = !isLeaseMusic;

        } catch (Exception e) {
            System.out.println("Musik konnte nicht gewechselt werden: " + e.getMessage());
        }
    }
}

