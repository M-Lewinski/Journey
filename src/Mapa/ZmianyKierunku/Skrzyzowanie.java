package Mapa.ZmianyKierunku;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Skrzyzowanie extends MiejsceZmianyKierunku {
    public Skrzyzowanie(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen,nazwa);
        this.setPromien(10);
    }
    @Override
    public void rysuj(Pane panel) {
        super.rysuj(panel);
        this.getImageNode().setStroke(Color.GREEN);
        this.getImageNode().setFill(Color.GREEN);
        panel.getChildren().add(this.getImageNode());
//        this.getImageNode().
    }
}
