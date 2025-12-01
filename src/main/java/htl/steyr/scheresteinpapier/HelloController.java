package htl.steyr.scheresteinpapier;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class HelloController {
    public ProgressBar pBar;
    public ImageView playerBox1; //Spieler-Bild
    public ImageView playerBox2; //Computer-Bild

    private final Random rand = new Random();
    public TextField spielerHighscore;
    public TextField computerHighscore;
    private String computer;

    /**
     * Wenn der/die Spieler/in auf den Schere-Button drückt, soll der passender
     * Image aufgeladen werden. Danach wird mainProcess() aufgerufen, wo die
     * Hauptfunktion stattfindet.
     */
    public void onScissorsClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/schere.png"))));
        mainProcess();
    }

    /**
     * In dieser Methode soll ein Bild von einem Stein aufgeladen werden. Diese
     * hat dieselbe Funktion wie die Methode drüber, nur mit einem anderen URL.
     */
    public void onRockClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/stein.png"))));
        mainProcess();
    }

    /**
     * In dieser Methode soll ein Bild von einem Papier aufgeladen werden.
     */
    public void onPaperClicked(ActionEvent actionEvent) {
        playerBox1.setImage(new Image(String.valueOf(getClass().getResource("/images/papier.png"))));
        mainProcess();
    }

    /**
     * In dieser Methode ist die Hauptfunktion implementiert worden. Sie ist
     * passend zu alle anderen Methoden, die diese aufrufen.
     */
    public void mainProcess() {
        String[] wahl = {"schere.png", "stein.png", "papier.png"}; //Dateioptionen werden bekannt gegeben

        //durch ".nextInt(3)" weiß der Computer, dass nur 3 Optionen zu verfügen stehen
        computer = wahl[rand.nextInt(3)]; //random wird eine Option gewählt

        //der ausgwählter Text wird hinter dem URL eingesetzt, wodurch der passende Image-URL ensteht
        playerBox2.setImage(new Image(String.valueOf(getClass().getResource("/images/" + computer))));
    }

    /**
     * Bringt alles auf den Anfangszustand - das Spiel kann neu beginnen.
     */
    public void resetButtonClicked(ActionEvent actionEvent) {
        playerBox1.setImage(null);
        playerBox2.setImage(null);
        spielerHighscore.setText("0");
        computerHighscore.setText("0");
    }
}
