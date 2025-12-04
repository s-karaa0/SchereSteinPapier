module htl.steyr.scheresteinpapier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens htl.steyr.scheresteinpapier to javafx.fxml;
    exports htl.steyr.scheresteinpapier;
}